package uestc.learning.service;

import org.apache.ibatis.session.SqlSession;

import uestc.learning.entity.Biker;
import uestc.learning.entity.Shopper;
import uestc.learning.mapper.BikerMapper;
import uestc.learning.mapper.ShopperMapper;
import uestc.learning.utils.D;
import uestc.learning.web.ApiResult;

public class ShopperServiceImplement implements ShopperService {

	SqlSession sqlSession = D.getConn();
	ShopperMapper shopperMapper = sqlSession.getMapper(ShopperMapper.class);
	
	public Shopper login(String[] params) {
		String username = params[0];
		String password = params[1];
		Shopper shopper = shopperMapper.getShopperByUsername(username);

		if(shopper != null && password.equals(shopper.password)) {
			System.out.println("Login success!");
			return shopper;
		}
		else {
			System.out.println("Login failure!");
			return null;			
		}
	}

	public ApiResult register(String[] params) {
		String username = params[0];
		String password = params[1];
		String manageres = params[2];
		
		int resid = Integer.parseInt(manageres);
		
		Shopper shopper = shopperMapper.getShopperByUsername(username);
		if(shopper!=null) {
			System.out.println("用户名已存在");
			return new ApiResult(404, "用户名已存在", null);
		}
		shopperMapper.addShopper(username, password, resid);
		sqlSession.commit();
		sqlSession.close();
		return new ApiResult(200, "注册成功", null);
	}

	public Shopper get(String username) {
		Shopper shopper  = shopperMapper.getShopperByUsername(username);
		return shopper;
	}
	
}
