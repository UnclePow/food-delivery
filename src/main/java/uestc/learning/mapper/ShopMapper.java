package uestc.learning.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import uestc.learning.entity.Shop;

public interface ShopMapper {
	public int getShopByKeywordCount(String keyword);
	
	public List<Shop> getShopsByKeyword(@Param("begin")int begin,@Param("pageSize")int pageSize, @Param("keyword")String keyword);
	
	public Shop getShopByResid(int resid);
}
