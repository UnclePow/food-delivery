package uestc.learning.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import uestc.learning.entity.Manager;
import uestc.learning.service.ManagerService;
import uestc.learning.service.ManagerServiceImplement;
import uestc.learning.web.ApiResult;

public class ManagerController {
	private ManagerService managerService;
	private ObjectMapper objectMapper;
	
	public  ManagerController() {
		managerService = new ManagerServiceImplement();
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
		Manager manger = managerService.login(values);
		if(manger == null) {
			resp.getWriter().write(objectMapper.writeValueAsString(new ApiResult(404, "请输入正确的用户名或密码", null)));
			return;//EXIT
		}
		
		
		HttpSession httpSession = req.getSession();
		httpSession.setAttribute("managerinfo", manger);
		
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("token", httpSession.getId());
		resp.getWriter().write(objectMapper.writeValueAsString(new ApiResult(200, "登录成功", map)));
	}
	
	public void add(HttpServletRequest req, HttpServletResponse resp) throws JsonProcessingException, IOException {
		String[] params = {"login_name", "password"};
		String[] values = {req.getParameter(params[0]), req.getParameter(params[1])};
		for (String item : values) {
			if(item == null || item.trim().length() == 0) {
				System.out.println("输入不合法");
				resp.getWriter().write(objectMapper.writeValueAsString(new ApiResult(404, "请输入完整用户名或密码", null)));
				return;//EXIT
			}
		}
		
		ApiResult result = managerService.add(values);
		resp.getWriter().write(objectMapper.writeValueAsString(result));
	}
}
