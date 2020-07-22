package uestc.learning.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.sf.json.JSONObject;
import uestc.learning.service.SMSService;
import uestc.learning.service.SMSServiceImplement;

public class SMSController {
	
	private ObjectMapper objectMapper;
	private SMSService smsService;
	
	public SMSController() {
		objectMapper = new ObjectMapper();
		smsService = new SMSServiceImplement();
		
	}
	
	
	public void send(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String param = "number";
		String phone = req.getParameter(param);
		String verifyCode = String.valueOf(new Random().nextInt(899999) + 100000);
		int availableTime = 5;
		String result = smsService.send(phone, "337", verifyCode, availableTime);
		
		//resp.getWriter().write(result);
		//String objectStr = objectMapper.writeValueAsString(result);
		JSONObject jsonObject=JSONObject.fromObject(result);
		String code = jsonObject.getString("code");
		String msg = jsonObject.getString("data");
		Map<String, Object> rs = new HashMap<String, Object>();
		rs.put("verifyCode", verifyCode);
		rs.put("availableTime", availableTime);
		rs.put("code", code);
		rs.put("msg", msg);
		//System.out.println(rs);
		resp.getWriter().write(objectMapper.writeValueAsString(rs));
		
	}
}
