package uestc.learning.service;


import uestc.learning.entity.Shopper;
import uestc.learning.web.ApiResult;

public interface ShopperService {
	public Shopper login(String[] params);
	
	public ApiResult register(String[] params);
	
	
	public Shopper get(String username);
}
