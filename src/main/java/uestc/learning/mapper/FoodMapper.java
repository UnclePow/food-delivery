package uestc.learning.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import uestc.learning.entity.Food;
import uestc.learning.entity.User;

public interface FoodMapper {
	public List<Food> getFoodsByResid(int foodid);
	
	public int getResidByFoodid(int foodid);
	
	public Food getFoodByFoodid(int foodid);
	
	int getFoodByKeywordCount(String keyword);
	
	List<Food> getFoodsByKeyword(@Param("begin")int begin,@Param("pageSize")int pageSize, @Param("keyword")String keyword);
}
