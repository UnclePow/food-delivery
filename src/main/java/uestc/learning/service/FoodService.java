package uestc.learning.service;

import java.util.List;

import uestc.learning.entity.Food;
import uestc.learning.web.Page;

public interface FoodService {
	public List<Food> list(int page_num, int page_size, String keyword, int resid);
	
	public Food getFoodById(int id);
	
	public Page<Food> list2(int page_num, int page_size, String keyword);

}
