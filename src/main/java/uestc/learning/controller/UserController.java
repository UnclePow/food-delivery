package uestc.learning.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import uestc.learning.entity.User;
import uestc.learning.service.UserService;
import uestc.learning.service.UserServiceImplement;
import uestc.learning.web.ApiResult;
import uestc.learning.web.Page;

public class UserController{
	private UserService userService;
	private ObjectMapper objectMapper;
	
	public  UserController() {
		userService = new UserServiceImplement();
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
		User user = userService.login(values);
		if(user == null) {
			resp.getWriter().write(objectMapper.writeValueAsString(new ApiResult(404, "请输入正确的用户名或密码", null)));
			return;//EXIT
		}
		
		
		HttpSession httpSession = req.getSession();
		httpSession.setAttribute("userinfo", user);
		
		
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
		
		ApiResult apiResult = userService.register(values);
		resp.getWriter().write(objectMapper.writeValueAsString(apiResult));
	}
	
	public void list(HttpServletRequest req, HttpServletResponse resp) throws JsonProcessingException, IOException {
		String[] params = {"page_num", "page_size", "keyword"};
		String[] values = {
				req.getParameter(params[0])==null||req.getParameter(params[0]).trim()=="" ? "1" : req.getParameter(params[0]),
				req.getParameter(params[1])==null||req.getParameter(params[1]).trim()=="" ? "10" : req.getParameter(params[1]),
				req.getParameter(params[2])==null||req.getParameter(params[2]).trim()=="" ? "" : req.getParameter(params[2])
		};
		int page_num = Integer.parseInt(values[0]);
		int page_size = Integer.parseInt(values[1]);
		String keyword = values[2];
		
		Page<User> page = userService.list(page_num, page_size, keyword);
		resp.getWriter().write(objectMapper.writeValueAsString(page));
	}
	
	public void get(HttpServletRequest req, HttpServletResponse resp) throws JsonProcessingException, IOException {
		String[] params = {"username"};
		String username = req.getParameter(params[0]);
		
		User user = userService.get(username);
		resp.getWriter().write(objectMapper.writeValueAsString(user));
	}
	
	public void delete(HttpServletRequest req, HttpServletResponse resp) throws JsonProcessingException, IOException {
		String[] params = {"login_name"};
		String[] values = {req.getParameter(params[0])};
		String username = values[0];
		ApiResult result = userService.delete(username);
		resp.getWriter().write(objectMapper.writeValueAsString(result));
	}
}
