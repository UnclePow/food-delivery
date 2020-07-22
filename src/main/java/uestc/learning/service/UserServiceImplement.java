package uestc.learning.service;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;


import uestc.learning.entity.User;
import uestc.learning.mapper.UserMapper;
import uestc.learning.utils.D;
import uestc.learning.web.ApiResult;
import uestc.learning.web.Page;

public class UserServiceImplement implements UserService{
	SqlSession sqlSession = D.getConn();
	UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
	
	public User login(String[] params){
		String username = params[0];
		String password = params[1];
		User user = userMapper.findUserById(username);

		if(user != null && password.equals(user.password)) {
			System.out.println("Login success!");
			return user;
		}
		else {
			System.out.println("Login failure!");
			return null;			
		}
	}


	public ApiResult register(String[] params) {
		String username = params[0];
		String password = params[1];
		String name = params[2];
		String tel = params[3];
		
		User user = userMapper.findUserById(username);
		if(user!=null) {
			System.out.println("用户名已存在");
			return new ApiResult(404, "用户名已存在", null);
		}
		userMapper.addUser(username, password, name, tel);
		sqlSession.commit();
		sqlSession.close();
		return new ApiResult(200, "注册成功", null);
	}


	public Page<User> list(int page_num, int page_size, String keyword) {
		int total = userMapper.getUserByKeywordCount(keyword);
		int begin = (page_num - 1) * page_size;
		List<User> users =  userMapper.getUsersByKeyword(begin, page_size, keyword);
		Page<User> page = new Page<User>(users, total, page_size, page_num);
		return page;
	}
	
	
	public User get(String username) {
		User user = userMapper.findUserById(username);
		return user;
	}


	public ApiResult delete(String username) {
		User user = userMapper.findUserById(username);
		if(user == null) {
			System.out.println("用户名不存在");
			return new ApiResult(404, "用户名不存在", null);
		}
		userMapper.deleteUserById(username);
		sqlSession.commit();
		sqlSession.close();
		return new ApiResult(200, "删除成功", null);
	}


	
}
