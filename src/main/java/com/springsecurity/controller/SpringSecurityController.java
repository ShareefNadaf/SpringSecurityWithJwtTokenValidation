package com.springsecurity.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.springsecurity.entity.UserData;
import com.springsecurity.entity.UserRequest;
import com.springsecurity.entity.UserResponse;
import com.springsecurity.service.SpringSecurityService;
import com.springsecurity.utilities.JwtToken;

@RestController
public class SpringSecurityController {

	@Autowired
	private SpringSecurityService springSecurityService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtToken jwtToken;
	private static final String errMessage = "User Not Found!";
	
	@PostMapping("/save")
	public ResponseEntity<String> saveUserData(@RequestBody UserData userData)
	{
		int userId = springSecurityService.saveUserData(userData);
		return ResponseEntity.ok("UserData '"+userId+"' Saved Successfuly!");
	}
	
	@GetMapping("/getuser/{userId}")
	public ResponseEntity<Object> getUserDataByUserId(@PathVariable int userId)
	{
		Optional<UserData> userData =springSecurityService.getUserDataById(userId);
		if(!userData.isPresent())
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errMessage);
		}
		
		return ResponseEntity.ok(userData);
	}
	
	@GetMapping("/getalluser")
	public ResponseEntity<List<UserData>> getAllUserData()
	{
		List<UserData> userData =springSecurityService.getAllUserData();
		return ResponseEntity.ok(userData);
	}
	
	@PutMapping("/updateuser")
	public ResponseEntity<String> updateUserData(@RequestBody UserData userData)
	{
		 boolean updatedUserData = springSecurityService.updateUserData(userData);
		 
		 if(updatedUserData)
		 {  
			 return ResponseEntity.ok("UserData '"+userData.getUserId()+"' Successfuly Updated.");
		 }
		 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errMessage);
	}
	
	@DeleteMapping("/deleteuser/{userId}")
	public ResponseEntity<String> deleteUserById(@PathVariable int userId)
	{
		 boolean deletedUser = springSecurityService.deleteUserDataById(userId);
		  if(deletedUser)
		  {  
			  return ResponseEntity.ok("User '"+userId+"' Deleted Successfuly!");
		  }
		  return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errMessage);
	}
	
	@PostMapping("/login")
	public ResponseEntity<UserResponse> login(@RequestBody UserRequest userRequest)
	{
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRequest.getUserName(), userRequest.getPassword()));
		String token = jwtToken.generateJwtToken(userRequest.getUserName());
		return ResponseEntity.ok(new UserResponse(token, "Success! Your Token is Generated."));
	}
	
}
