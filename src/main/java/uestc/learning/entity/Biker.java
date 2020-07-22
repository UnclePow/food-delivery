package uestc.learning.entity;

public class Biker {
	public int bikerid;
	public String bikername;
	public String password;
	public String name;
	public String tel;
	public int status;
	
	
	public int getBikerid() {
		return bikerid;
	}
	public void setBikerid(int bikerid) {
		this.bikerid = bikerid;
	}
	public String getBikername() {
		return bikername;
	}
	public void setBikername(String bikername) {
		this.bikername = bikername;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "Biker [bikerid=" + bikerid + ", bikername=" + bikername + ", password=" + password + ", name=" + name
				+ ", tel=" + tel + ", status=" + status + "]";
	}
	
	
}
