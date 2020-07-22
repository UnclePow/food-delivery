package uestc.learning.utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


public class D {
	public static SqlSession getConn(){
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream("mybatis-config.xml");
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			return sqlSessionFactory.openSession();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("ERROR");
			return null;
		}
	}
	
	public static void main(String[] args) throws IOException {
		System.out.print(D.getConn());
	}
}
