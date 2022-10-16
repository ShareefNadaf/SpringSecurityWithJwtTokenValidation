package com.springsecurity.service;

import java.util.List;
import java.util.Optional;
import com.springsecurity.entity.UserData;


public interface SpringSecurityService {

	int saveUserData(UserData userData);
	Optional<UserData> getUserDataById(int userId); 
	List<UserData> getAllUserData();
	int updateUserData(UserData userData);
	void deleteUserDataById(int userId);
}
