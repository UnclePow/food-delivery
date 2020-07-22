package uestc.learning.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import uestc.learning.entity.Biker;
import uestc.learning.web.ApiResult;

public interface BikerMapper {
	Biker getBikerByBikername(String bikername);
	
	Biker getBikerByBikernameAndPassword(String bikername, String password);
	
	void addBiker(String bikername, String password, String name, String tel);
	
	List<Biker> getBikersByStatus(int status);
	
	void updateBikerStatus(@Param("bikerid")int bikerid, @Param("status") int status);
}
