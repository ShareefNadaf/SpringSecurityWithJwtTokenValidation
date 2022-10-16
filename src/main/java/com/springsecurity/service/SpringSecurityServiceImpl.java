package com.springsecurity.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.springsecurity.dao.SpringSecurityDao;
import com.springsecurity.entity.UserData;

@Service
public class SpringSecurityServiceImpl implements SpringSecurityService {

	@Autowired
	private SpringSecurityDao springSecurityDao;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public int saveUserData(UserData userData) {
	
		userData.setPassword(bCryptPasswordEncoder.encode(userData.getPassword()));
		int userId = springSecurityDao.save(userData).getUserId();
		return userId;
	}

	@Override
	public Optional<UserData> getUserDataById(int userId) {

		Optional<UserData> userData = springSecurityDao.findById(userId);
		return userData;
	}

	@Override
	public List<UserData> getAllUserData() {

		List<UserData> userData = springSecurityDao.findAll();
		return userData;
	}

	@Override
	public int updateUserData(UserData userData) {
		userData.setPassword(bCryptPasswordEncoder.encode(userData.getPassword()));
		int userId = springSecurityDao.save(userData).getUserId();
		
		return userId;
	}

	@Override
	public void deleteUserDataById(int userId) {

		springSecurityDao.deleteById(userId);
	}

}
