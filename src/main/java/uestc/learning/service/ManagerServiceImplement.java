package uestc.learning.service;

import org.apache.ibatis.jdbc.Null;
import org.apache.ibatis.session.SqlSession;

import uestc.learning.entity.Manager;
import uestc.learning.mapper.ManagerMapper;
import uestc.learning.mapper.UserMapper;
import uestc.learning.utils.D;
import uestc.learning.web.ApiResult;

public class ManagerServiceImplement implements ManagerService{
	SqlSession sqlSession = D.getConn();
	ManagerMapper managerMapper = sqlSession.getMapper(ManagerMapper.class);
	
	
	public Manager login(String[] params) {
		String username = params[0];
		String password = params[1];
		
		Manager manager = managerMapper.findManagerByUsername(username);
		if(manager != null && password.equals(manager.password)) {
			System.out.println("Login success!");
			return manager;
		}
		else {
			System.out.println("Login failure!");
			return null;			
		}
	}


	public ApiResult add(String[] params) {
		String username = params[0];
		String password = params[1];
		Manager manager = managerMapper.findManagerByUsername(username);
		if(manager != null) {
			System.out.println("用户名已处在");
			return new ApiResult(404, "用户名已处在", null);
		}
		
		managerMapper.addManager(username, password);
		sqlSession.commit();
		sqlSession.close();
		return new ApiResult(200, "添加成功", null);
	}

}
