package uestc.learning.service;

import java.io.IOException;

import uestc.learning.entity.User;
import uestc.learning.web.ApiResult;
import uestc.learning.web.Page;

public interface UserService{
	public User login(String[] params);
	
	public ApiResult register(String[] params);
	
	public Page<User> list(int page_num, int page_size, String keyword);
	
	public ApiResult delete(String username);
	
	public User get(String username);
}
