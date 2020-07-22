package uestc.learning.entity;

public class Food {
	public String price;
	public String name;
	public String resid;
	public String imageurl;
	public String foodid;
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getResid() {
		return resid;
	}
	public void setResid(String resid) {
		this.resid = resid;
	}
	public String getImageurl() {
		return imageurl;
	}
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	public String getFoodid() {
		return foodid;
	}
	public void setFoodid(String foodid) {
		this.foodid = foodid;
	}
	
	@Override
	public String toString() {
		return "Food [price=" + price + ", name=" + name + ", resid=" + resid + ", imageurl=" + imageurl + ", foodid="
				+ foodid + "]";
	}
	
	
}
