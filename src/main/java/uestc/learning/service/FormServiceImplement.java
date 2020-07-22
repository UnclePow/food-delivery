package uestc.learning.service;

import java.util.LinkedList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import uestc.learning.entity.Food;
import uestc.learning.entity.Form;
import uestc.learning.mapper.FormMapper;
import uestc.learning.utils.D;
import uestc.learning.web.ApiResult;
import uestc.learning.web.Page;

public class FormServiceImplement implements FormService{
	SqlSession sqlSession = D.getConn();
	FormMapper formMapper = sqlSession.getMapper(FormMapper.class);
	
	public ApiResult add(int[] foodids, String userid, String address, String info, int resid) {
		formMapper.addForm(foodids, userid, address, info, resid);
		sqlSession.commit();
		sqlSession.close();
		return new ApiResult(200, "添加成功", null);
	}

	public List<Form> list(String userid) {
		List<Form> forms = formMapper.getFormsByUserid(userid);
		return forms;
	}

	public List<Form> listByResid(int resid) {
		List<Form> forms = formMapper.getFormsByResid(resid);
		return forms;
	}
	

	public List<Form> listByResidNStatus(int resid, String[] statuses) {
		List<Form> forms = new LinkedList<Form>();
		for (String status : statuses) {
			forms.addAll(formMapper.getFormsByResidNStatus(resid, status));
		}
		
		return forms;
	}
	public List<Form> listByBikeridNStatus(int bikerid, String[] statuses) {
		List<Form> forms = new LinkedList<Form>();
		for (String status : statuses) {
			forms.addAll(formMapper.getFormsByBikeridNStatus(bikerid, status));
		}
		
		return forms;
	}
	

	public ApiResult updateStatus(int formid, String status, int bikerid) {
		formMapper.updateFormStatus(formid, status, bikerid);
		sqlSession.commit();
		sqlSession.close();
		return new ApiResult(200, "操作成功", null);
	}

	public Page<Form> list2(int page_num, int page_size, String keyword) {
		int total = formMapper.getFormByKeywordCount(keyword);
		int begin = (page_num - 1) * page_size;
		List<Form> foods =  formMapper.getFormsByKeyword(begin, page_size, keyword);
		Page<Form> page = new Page<Form>(foods, total, page_size, page_num);
		return page;
	}

}
