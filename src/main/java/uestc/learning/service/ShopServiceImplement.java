package uestc.learning.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import uestc.learning.entity.Shop;
import uestc.learning.mapper.FoodMapper;
import uestc.learning.mapper.ShopMapper;
import uestc.learning.utils.D;
import uestc.learning.web.Page;

public class ShopServiceImplement implements ShopService{
	SqlSession sqlSession = D.getConn();
	ShopMapper shopMapper = sqlSession.getMapper(ShopMapper.class);
	FoodMapper foodMapper = sqlSession.getMapper(FoodMapper.class);

	public Page<Shop> list(int page_num, int page_size, String keyword) {
		int total = shopMapper.getShopByKeywordCount(keyword);
		int begin = (page_num - 1) * page_size;
		List<Shop> users =  shopMapper.getShopsByKeyword(begin, page_size, keyword);
		Page<Shop> page = new Page<Shop>(users, total, page_size, page_num);
		return page;
	}

	public Shop getShop(int foodid) {
		 int resid = foodMapper.getResidByFoodid(foodid);
		 Shop shop = shopMapper.getShopByResid(resid);
		 return shop;
	}
	
}
