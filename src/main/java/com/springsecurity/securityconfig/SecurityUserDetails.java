package com.springsecurity.securityconfig;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.springsecurity.dao.SpringSecurityDao;
import com.springsecurity.entity.UserData;

@Component
public class SecurityUserDetails implements UserDetailsService {

	@Autowired
	private SpringSecurityDao springSecurityDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<UserData> userData = springSecurityDao.findUserByUserName(username);
		if(userData.isEmpty())
		{
			throw new UsernameNotFoundException("User is Not Avaliable"+username);
		}
		
		return new User(username, userData.get().getPassword(), userData.get().getRoles()
				.stream().map(roles->new SimpleGrantedAuthority(roles))
				.collect(Collectors.toList()));
	}

	
}
