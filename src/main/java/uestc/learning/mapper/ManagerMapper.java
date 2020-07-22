package uestc.learning.mapper;

import uestc.learning.entity.Manager;

public interface ManagerMapper {
	Manager findManagerByUsername(String username);
	
	void addManager(String username, String password);
}
