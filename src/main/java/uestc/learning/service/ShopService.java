package uestc.learning.service;

import java.util.List;

import uestc.learning.entity.Shop;
import uestc.learning.web.Page;

public interface ShopService {
	Page<Shop> list(int page_num, int page_size, String keyword);
	
	public Shop getShop(int foodid);
}
