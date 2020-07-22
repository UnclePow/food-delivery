package uestc.learning.entity;

public class Shopper {
	public int shopperid;
	public String username;
	public String password;
	public int manageres;
	
	public int getShopperid() {
		return shopperid;
	}
	public void setShopperid(int shopperid) {
		this.shopperid = shopperid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getManageres() {
		return manageres;
	}
	public void setManageres(int manageres) {
		this.manageres = manageres;
	}
	@Override
	public String toString() {
		return "Shopper [shopperid=" + shopperid + ", username=" + username + ", password=" + password + ", manageres="
				+ manageres + "]";
	}
}
