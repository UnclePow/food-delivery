package uestc.learning.entity;

public class Form {
	public String formid;
	public String foodids;
	public String userid;
	public String createtime;
	public String address;
	public String info;
	public String status;
	public int bikerid;
	public int resid;
	
	
	public int getBikerid() {
		return bikerid;
	}
	public void setBikerid(int bikerid) {
		this.bikerid = bikerid;
	}		
	public int getResid() {
		return resid;
	}
	public void setResid(int resid) {
		this.resid = resid;
	}
	public String getFormid() {
		return formid;
	}
	public void setFormid(String formid) {
		this.formid = formid;
	}
	public String getFoodids() {
		return foodids;
	}
	public void setFoodids(String foodids) {
		this.foodids = foodids;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	@Override
	public String toString() {
		return "Form [formid=" + formid + ", foodids=" + foodids + ", userid=" + userid + ", createtime=" + createtime
				+ ", address=" + address + ", info=" + info + ", status=" + status + ", bikerid=" + bikerid + ", resid="
				+ resid + "]";
	}	
}
