package uestc.learning.service;

import java.util.List;

import uestc.learning.entity.Food;
import uestc.learning.entity.Form;
import uestc.learning.web.ApiResult;
import uestc.learning.web.Page;

public interface FormService {
	public ApiResult add(int[] foodids, String userid, String address, String info, int resid);
	
	public List<Form> list(String userid);
	
	public List<Form> listByResid(int resid);
	
	public List<Form> listByResidNStatus(int resid, String[] statuses);
	public List<Form> listByBikeridNStatus(int bikerid, String[] statuses);
	
	public ApiResult updateStatus(int formid, String status, int bikerid);
	
	public Page<Form> list2(int page_num, int page_size, String keyword);
}
