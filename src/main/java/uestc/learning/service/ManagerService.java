package uestc.learning.service;

import uestc.learning.entity.Manager;
import uestc.learning.web.ApiResult;

public interface ManagerService {
	Manager login(String[] params);
	
	ApiResult add(String[] params);
}
