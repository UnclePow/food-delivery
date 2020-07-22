package uestc.learning.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import uestc.learning.entity.Food;
import uestc.learning.entity.Form;

public interface FormMapper {
	void addForm(@Param("foodids")int[] foodids, @Param("userid")String userid, @Param("address")String address, @Param("info")String info, @Param("resid")int resid);
	
	List<Form> getFormsByUserid(String userid);
	
	List<Form> getFormsByResid(int resid);
	
	List<Form> getFormsByResidNStatus(@Param("resid")int resid, @Param("status")String status);
	List<Form> getFormsByBikeridNStatus(@Param("bikerid")int bikerid, @Param("status")String status);
	
	void updateFormStatus(@Param("formid")int formid, @Param("status") String status, @Param("bikerid")int bikerid);
	
	int getFormByKeywordCount(String keyword);
	
	List<Form> getFormsByKeyword(@Param("begin")int begin,@Param("pageSize")int pageSize, @Param("keyword")String keyword);
}
