package uestc.learning.service;

public interface SMSService {
	public String send(String number, String templateId, String verifyCode, int availableTime) throws Exception;
}
