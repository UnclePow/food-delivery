package uestc.learning.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import uestc.learning.entity.Shopper;
import uestc.learning.service.ShopperService;
import uestc.learning.service.ShopperServiceImplement;
import uestc.learning.web.ApiResult;

public class ShopperController {
	private ShopperService shopperService;
	private ObjectMapper objectMapper;
	
	public ShopperController() {
		shopperService = new ShopperServiceImplement();
		objectMapper = new ObjectMapper();
	}
	
	public void login(HttpServletRequest req, HttpServletResponse resp) throws JsonProcessingException, IOException {
		String[] params = {"username", "password"};
		String[] values = {req.getParameter(params[0]), req.getParameter(params[1])};
		for (String item : values) {
			if(item == null || item.trim().length() == 0) {
				System.out.println("输入不合法");
				resp.getWriter().write(objectMapper.writeValueAsString(new ApiResult(404, "请输入完整用户名或密码", null)));
				return;//EXIT
			}
		}
		
		//LOGIN!!!
		Shopper shopper = shopperService.login(values);
		if(shopper == null) {
			resp.getWriter().write(objectMapper.writeValueAsString(new ApiResult(404, "请输入正确的用户名或密码", null)));
			return;//EXIT
		}
		
		
		HttpSession httpSession = req.getSession();
		httpSession.setAttribute("userinfo", shopper);
		
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("token", httpSession.getId());
		resp.getWriter().write(objectMapper.writeValueAsString(new ApiResult(200, "登录成功", map)));
	}
	
	public void register(HttpServletRequest req, HttpServletResponse resp) throws JsonProcessingException, IOException {
		String[] params = {"login_name", "password", "resid"};
		String[] values = {req.getParameter(params[0]), req.getParameter(params[1]),req.getParameter(params[2])};
		for (String item : values) {
			if(item == null || item.trim().length() == 0) {
				System.out.println("输入不合法");
				resp.getWriter().write(objectMapper.writeValueAsString(new ApiResult(404, "请输入完整", null)));
				return;//EXIT
			}
		}
		
		ApiResult apiResult = shopperService.register(values);
		resp.getWriter().write(objectMapper.writeValueAsString(apiResult));
	}
	
	public void get(HttpServletRequest req, HttpServletResponse resp) throws JsonProcessingException, IOException {
		String[] params = {"username"};
		String username = req.getParameter(params[0]);
		Shopper shopper = shopperService.get(username);
		resp.getWriter().write(objectMapper.writeValueAsString(shopper));
	}
}
