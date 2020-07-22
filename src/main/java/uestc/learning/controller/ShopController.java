package uestc.learning.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import uestc.learning.entity.Shop;
import uestc.learning.service.ShopService;
import uestc.learning.service.ShopServiceImplement;
import uestc.learning.web.Page;

public class ShopController {
	private ShopService shopService;
	private ObjectMapper objectMapper;
	
	public  ShopController() {
		shopService = new ShopServiceImplement();
		objectMapper = new ObjectMapper();
	}
	
	
	public void add(HttpServletRequest req, HttpServletResponse resp) {
		
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
		
		Page<Shop> page = shopService.list(page_num, page_size, keyword);
		resp.getWriter().write(objectMapper.writeValueAsString(page));
	}
	
	public void getShop(HttpServletRequest req, HttpServletResponse resp) throws JsonProcessingException, IOException {
		String[] params = {"foodid"};
		int foodid = Integer.parseInt(req.getParameter(params[0]));
		
		Shop shop = shopService.getShop(foodid);
		resp.getWriter().write(objectMapper.writeValueAsString(shop));
	}
	
}
