package com.springsecurity.entity;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;

@Entity
public class UserData {

	@Id
	@GeneratedValue(generator = "userDataId",strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "userDataId",sequenceName = "userDataId")
	private int userId;
	private String name;
	private String userName;
	private String password;
	private String address;
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(joinColumns = @JoinColumn(name = "userId"))
	@Column(name = "Roles")
	private Set<String> roles;
	
	public UserData() {
		
	}

	public UserData(int userId, String name, String userName, String password, String address, Set<String> roles) {
		super();
		this.userId = userId;
		this.name = name;
		this.userName = userName;
		this.password = password;
		this.address = address;
		this.roles = roles;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "UserData [userId=" + userId + ", name=" + name + ", userName=" + userName + ", password=" + password
				+ ", address=" + address + ", roles=" + roles + "]";
	}
	
	
	
	
}
