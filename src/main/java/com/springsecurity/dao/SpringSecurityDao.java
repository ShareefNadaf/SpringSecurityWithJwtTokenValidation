package com.springsecurity.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.springsecurity.entity.UserData;

@Repository
public interface SpringSecurityDao extends JpaRepository<UserData, Integer>  {

	Optional<UserData> findUserByUserName(String userName);
}
