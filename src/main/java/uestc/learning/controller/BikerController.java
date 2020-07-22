package uestc.learning.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import uestc.learning.entity.Biker;
import uestc.learning.service.BikerService;
import uestc.learning.service.BikerServiceImplement;
import uestc.learning.web.ApiResult;


public class BikerController {
	private BikerService bikerService;
	private ObjectMapper objectMapper;
	
	public  BikerController() {
		bikerService = new BikerServiceImplement();
		objectMapper = new ObjectMapper();
	}
	
	public void login(HttpServletRequest req, HttpServletResponse resp) throws JsonProcessingException, IOException {
		String[] params = {"login_name", "password"};
		String[] values = {req.getParameter(params[0]), req.getParameter(params[1])};
		for (String item : values) {
			if(item == null || item.trim().length() == 0) {
				System.out.println("输入不合法");
				resp.getWriter().write(objectMapper.writeValueAsString(new ApiResult(404, "请输入完整用户名或密码", null)));
				return;//EXIT
			}
		}
		
		//LOGIN!!!
		Biker biker = bikerService.login(values);
		if(biker == null) {
			resp.getWriter().write(objectMapper.writeValueAsString(new ApiResult(404, "请输入正确的用户名或密码", null)));
			return;//EXIT
		}
		
		
		HttpSession httpSession = req.getSession();
		httpSession.setAttribute("userinfo", biker);
		
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("token", httpSession.getId());
		resp.getWriter().write(objectMapper.writeValueAsString(new ApiResult(200, "登录成功", map)));
	}
	
	public void register(HttpServletRequest req, HttpServletResponse resp) throws JsonProcessingException, IOException {
		String[] params = {"login_name", "password", "name", "tel"};
		String[] values = {req.getParameter(params[0]), req.getParameter(params[1]),req.getParameter(params[2]),req.getParameter(params[3])};
		for (String item : values) {
			if(item == null || item.trim().length() == 0) {
				System.out.println("输入不合法");
				resp.getWriter().write(objectMapper.writeValueAsString(new ApiResult(404, "请输入完整", null)));
				return;//EXIT
			}
		}
		
		ApiResult apiResult = bikerService.register(values);
		resp.getWriter().write(objectMapper.writeValueAsString(apiResult));
	}
	
	public void list(HttpServletRequest req, HttpServletResponse resp) throws JsonProcessingException, IOException {
		String[] params = {"status"};
		int status  = Integer.parseInt(req.getParameter(params[0]));
		
		List<Biker> bikers = bikerService.list(status);
		resp.getWriter().write(objectMapper.writeValueAsString(bikers));
	}
	
	public void get(HttpServletRequest req, HttpServletResponse resp) throws JsonProcessingException, IOException {
		String[] params = {"bikername"};
		String bikername = req.getParameter(params[0]);
		
		Biker biker = bikerService.get(bikername);
		resp.getWriter().write(objectMapper.writeValueAsString(biker));
	}
	
	public void updateStatus(HttpServletRequest req, HttpServletResponse resp) throws JsonProcessingException, IOException {
		String[] params = {"bikerid", "status"};
		int bikerid = Integer.parseInt(req.getParameter(params[0]));
		int status = Integer.parseInt(req.getParameter(params[1]));
		
		ApiResult result = bikerService.updateStatus(bikerid, status);
		resp.getWriter().write(objectMapper.writeValueAsString(result));
						
	}
	
}
