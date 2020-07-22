package uestc.learning.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import uestc.learning.entity.Biker;
import uestc.learning.mapper.BikerMapper;
import uestc.learning.utils.D;
import uestc.learning.web.ApiResult;

public class BikerServiceImplement implements BikerService{
	SqlSession sqlSession = D.getConn();
	BikerMapper bikerMapper = sqlSession.getMapper(BikerMapper.class);
	
	public Biker login(String[] params){
		String bikername = params[0];
		String password = params[1];
		Biker biker = bikerMapper.getBikerByBikername(bikername);

		if(biker != null && password.equals(biker.password)) {
			System.out.println("Login success!");
			return biker;
		}
		else {
			System.out.println("Login failure!");
			return null;			
		}
	}


	public ApiResult register(String[] params) {
		String bikername = params[0];
		String password = params[1];
		String name = params[2];
		String tel = params[3];
		
		Biker biker = bikerMapper.getBikerByBikername(bikername);
		if(biker!=null) {
			System.out.println("用户名已存在");
			return new ApiResult(404, "用户名已存在", null);
		}
		bikerMapper.addBiker(bikername, password, name, tel);
		sqlSession.commit();
		sqlSession.close();
		return new ApiResult(200, "注册成功", null);
	}


	public List<Biker> list(int status) {
		List<Biker> bikers = bikerMapper.getBikersByStatus(status);
		return bikers;
	}


	public Biker get(String bikername) {
		Biker biker  = bikerMapper.getBikerByBikername(bikername);
		return biker;
	}


	public ApiResult updateStatus(int bikerid, int status) {
		bikerMapper.updateBikerStatus(bikerid, status);
		sqlSession.commit();
		sqlSession.close();
		return new ApiResult(200, "操作成功", null);
	}
}
