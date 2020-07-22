package uestc.learning.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import uestc.learning.entity.User;

public interface UserMapper {
	
	User findUserById(String id);
	
	User findUserByUsernameAndPassword(String username, String password);
	
	void addUser(String id, String password, String name, String tel);
	
	int getUserByKeywordCount(String keyword);
	
	List<User> getUsersByKeyword(@Param("begin")int begin,@Param("pageSize")int pageSize, @Param("keyword")String keyword);
	
	void deleteUserById(String username);
}
