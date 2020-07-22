package uestc.learning.entity;

public class Shop {
	public String resid;
	public String name;
	public String address;
	public String phone;
	public String imageurl;
	public String getResid() {
		return resid;
	}
	public void setResid(String resid) {
		this.resid = resid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getImageurl() {
		return imageurl;
	}
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	
	@Override
	public String toString() {
		return "Shop [resid=" + resid + ", name=" + name + ", address=" + address + ", phone=" + phone + ", imageurl="
				+ imageurl + "]";
	}
}
