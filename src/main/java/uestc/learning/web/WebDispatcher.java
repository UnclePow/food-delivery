package uestc.learning.web;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import uestc.learning.utils.NameUtils;


@WebServlet("/*")
public class WebDispatcher extends HttpServlet {
	public static final String PROJECT_NAME = "/learning/";
	public static final String PACKAGE_NAME = "uestc.learning.controller";
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		//‘ –ÌøÁ”Ú∑√Œ 
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setContentType("application/json");
		resp.setCharacterEncoding("utf-8");
		
		String uri = req.getRequestURI();//		/learning/user/login
		
		uri = uri.replace(PROJECT_NAME, "");
		
		String[] reqUri = uri.split("/");
		if(reqUri.length < 2) {
			System.out.println("ERROR");
			return;
		}
		String cat = reqUri[0];
		String opt = reqUri[1];
		cat = NameUtils.convert2Camel(cat);
		cat = NameUtils.firstUpper(cat);
		opt = NameUtils.convert2Camel(opt);
		
		String catName = PACKAGE_NAME + "." +cat + "Controller";
		
		try {
			Class<?> cls = Class.forName(catName);
			Object instance = cls.newInstance();
			Method method = cls.getMethod(opt, HttpServletRequest.class,HttpServletResponse.class);
			Object obj = method.invoke(instance, req,resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
