package uestc.learning.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.zhenzi.sms.ZhenziSmsClient;

public class SMSServiceImplement implements SMSService{

	public String send(String number, String templateId, String verifyCode, int availableTime) throws Exception {
		ZhenziSmsClient client = new ZhenziSmsClient("https://sms_developer.zhenzikj.com", "105913", "79aed4a7-fce6-4a47-af73-7e3fcb878f2f");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("number", number);
		params.put("templateId", templateId);
		String[] templateParams = new String[2];
		templateParams[0] = verifyCode;
		templateParams[1] = availableTime + "∑÷÷”";
		params.put("templateParams", templateParams);
		String result = client.send(params);
		return result;
	}
}
