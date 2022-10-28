package com.springsecurity.service;

import java.util.List;
import java.util.Optional;
import com.springsecurity.entity.UserData;


public interface SpringSecurityService {

	int saveUserData(UserData userData);
	Optional<UserData> getUserDataById(int userId); 
	List<UserData> getAllUserData();
	boolean updateUserData(UserData userData);
	boolean deleteUserDataById(int userId);
}
