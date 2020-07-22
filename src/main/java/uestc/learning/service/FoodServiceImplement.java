package uestc.learning.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import uestc.learning.entity.Food;
import uestc.learning.entity.User;
import uestc.learning.mapper.FoodMapper;
import uestc.learning.utils.D;
import uestc.learning.web.Page;

public class FoodServiceImplement implements FoodService{
	SqlSession sqlSession = D.getConn();
	FoodMapper foodMapper = sqlSession.getMapper(FoodMapper.class);
	
	public List<Food> list(int page_num, int page_size, String keyword, int resid) {
		List<Food> foods = foodMapper.getFoodsByResid(resid);
		return foods;
	}

	public Food getFoodById(int id) {
		Food food = foodMapper.getFoodByFoodid(id);
		return food;		
	}

	public Page<Food> list2(int page_num, int page_size, String keyword) {
		int total = foodMapper.getFoodByKeywordCount(keyword);
		int begin = (page_num - 1) * page_size;
		List<Food> foods =  foodMapper.getFoodsByKeyword(begin, page_size, keyword);
		Page<Food> page = new Page<Food>(foods, total, page_size, page_num);
		return page;
	}	
}
