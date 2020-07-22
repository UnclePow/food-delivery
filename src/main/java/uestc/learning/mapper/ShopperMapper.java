package uestc.learning.mapper;

import uestc.learning.entity.Shopper;

public interface ShopperMapper {
	public Shopper getShopperByUsername(String username);
	
	public Shopper getShopperByUsernameAndPassword(String username, String password);
	
	public void addShopper(String username, String password, int manageres); 
}
