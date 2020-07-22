package uestc.learning.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import uestc.learning.entity.Food;
import uestc.learning.entity.Form;
import uestc.learning.service.FormService;
import uestc.learning.service.FormServiceImplement;
import uestc.learning.web.ApiResult;
import uestc.learning.web.Page;

public class FormController {
	private FormService formService;
	private ObjectMapper objectMapper;
	
	public  FormController() {
		formService = new FormServiceImplement();
		objectMapper = new ObjectMapper();
	}
	
	public void add(HttpServletRequest req, HttpServletResponse resp) throws JsonProcessingException, IOException {
		String[] params = {"foodids", "userid", "address", "info", "resid"};
		String foodids = req.getParameter(params[0]);//{1,2,3} {1}
		String userid = req.getParameter(params[1]);
		String address = req.getParameter(params[2])==null || req.getParameter(params[2]).trim()=="" ? "此用户暂未填写任何地址信息" : req.getParameter(params[2]);
		String info = req.getParameter(params[3])==null || req.getParameter(params[3]).trim()=="" ? "此用户暂未填写任何备注信息" : req.getParameter(params[3]);
		int resid = Integer.parseInt(req.getParameter(params[4]));
		
		String[] splits = foodids.split(",");
		int[] splits_int = new int[splits.length];
		try {
			for(int i = 0; i < splits.length; i++) {
				splits_int[i] = Integer.parseInt(splits[i]);
			}
		}catch (Exception e) {
			resp.getWriter().write(objectMapper.writeValueAsString(new ApiResult(404, "参数错误", null)));	
			return;
		}
		ApiResult result = formService.add(splits_int, userid, address, info, resid);
		resp.getWriter().write(objectMapper.writeValueAsString(result));		
	}
	
	public void list(HttpServletRequest req, HttpServletResponse resp) throws JsonProcessingException, IOException {
		String[] params = {"userid"};
		String userid = req.getParameter(params[0]);
		
		List<Form> forms = formService.list(userid);
		resp.getWriter().write(objectMapper.writeValueAsString(forms));
	}
	
	public void listForms(HttpServletRequest req, HttpServletResponse resp) throws JsonProcessingException, IOException {
		String[] params = {"resid", "bikerid", "status"};//注意：传入的参树resid，status 或 bikerid，status 二选一
		
		
		String status = req.getParameter(params[2]);
		String[] statuses = status.split(",");
		if(req.getParameter(params[0]) != null) {//说明传入的参数为 resid，status
			int resid = Integer.parseInt(req.getParameter(params[0]));
			List<Form> forms = formService.listByResidNStatus(resid, statuses);
			resp.getWriter().write(objectMapper.writeValueAsString(forms));
		}
		else {//说明传入的参数为 bikerid，status
			int bikerid = Integer.parseInt(req.getParameter(params[1]));
			List<Form> forms = formService.listByBikeridNStatus(bikerid, statuses);
			resp.getWriter().write(objectMapper.writeValueAsString(forms));
		}			
	}
	
	public void updateStatus(HttpServletRequest req, HttpServletResponse resp) throws JsonProcessingException, IOException {
		String[] params = {"formid", "status", "bikerid"};
		int formid = Integer.parseInt(req.getParameter(params[0]));
		String status = req.getParameter(params[1]);
		int bikerid = Integer.parseInt(req.getParameter(params[2]));
		
		ApiResult result = formService.updateStatus(formid, status, bikerid);
		
		resp.getWriter().write(objectMapper.writeValueAsString(result));
	}
	
	public void list2(HttpServletRequest req, HttpServletResponse resp) throws JsonProcessingException, IOException {
		String[] params = {"page_num", "page_size", "keyword"};
		String[] values = {
				req.getParameter(params[0])==null||req.getParameter(params[0]).trim()=="" ? "1" : req.getParameter(params[0]),
				req.getParameter(params[1])==null||req.getParameter(params[1]).trim()=="" ? "10" : req.getParameter(params[1]),
				req.getParameter(params[2])==null||req.getParameter(params[2]).trim()=="" ? "" : req.getParameter(params[2])
		};
		int page_num = Integer.parseInt(values[0]);
		int page_size = Integer.parseInt(values[1]);
		String keyword = values[2];
		
		Page<Form> page = formService.list2(page_num, page_size, keyword);
		resp.getWriter().write(objectMapper.writeValueAsString(page));
	}
	
}
