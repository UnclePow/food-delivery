package uestc.learning.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import uestc.learning.entity.Food;
import uestc.learning.entity.User;
import uestc.learning.service.FoodService;
import uestc.learning.service.FoodServiceImplement;
import uestc.learning.web.Page;

public class FoodController {
	private FoodService foodService;
	private ObjectMapper objectMapper;
	
	public  FoodController() {
		foodService = new FoodServiceImplement();
		objectMapper = new ObjectMapper();
	}
	
	public void list(HttpServletRequest req, HttpServletResponse resp) throws JsonProcessingException, IOException {
		String[] params = {"page_num", "page_size", "keyword", "resid"};
		String[] values = {
				req.getParameter(params[0])==null||req.getParameter(params[0]).trim()=="" ? "1" : req.getParameter(params[0]),
				req.getParameter(params[1])==null||req.getParameter(params[1]).trim()=="" ? "1000" : req.getParameter(params[1]),
				req.getParameter(params[2])==null||req.getParameter(params[2]).trim()=="" ? "" : req.getParameter(params[2]),
				req.getParameter(params[3])==null||req.getParameter(params[3]).trim()=="1" ? "" : req.getParameter(params[3])
		};
		int page_num = Integer.parseInt(values[0]);
		int page_size = Integer.parseInt(values[1]);
		String keyword = values[2];
		int resid = Integer.parseInt(values[3]);
		
		List<Food> foods = foodService.list(page_num, page_size, keyword, resid);
		resp.getWriter().write(objectMapper.writeValueAsString(foods));
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
		
		Page<Food> page = foodService.list2(page_num, page_size, keyword);
		resp.getWriter().write(objectMapper.writeValueAsString(page));
	}
	
	public void getFood(HttpServletRequest req, HttpServletResponse resp) throws JsonProcessingException, IOException {
		String param = "foodid";
		int foodid = Integer.parseInt(req.getParameter(param));
		
		Food food = foodService.getFoodById(foodid);
		resp.getWriter().write(objectMapper.writeValueAsString(food));
	}
}
