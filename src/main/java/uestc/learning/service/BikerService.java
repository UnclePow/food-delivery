package uestc.learning.service;

import java.util.List;

import uestc.learning.entity.Biker;
import uestc.learning.web.ApiResult;

public interface BikerService {
	
	public Biker login(String[] params);
	
	public ApiResult register(String[] params);
	
	public List<Biker> list(int status);
	
	public Biker get(String bikername);
	
	public ApiResult updateStatus(int bikerid, int status);
}
