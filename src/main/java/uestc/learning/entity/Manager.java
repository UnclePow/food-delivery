package uestc.learning.entity;

public class Manager {
	public String id;
	public String password;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "Manager [id=" + id + ", password=" + password + "]";
	}
}
