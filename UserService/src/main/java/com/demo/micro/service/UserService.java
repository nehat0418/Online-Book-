package com.demo.micro.service;

import java.util.List;

import com.demo.micro.entities.User;

public interface UserService {
	
	User saveUser(User user);
	List<User> getAllUser();
	User getUser(String userId);
	User updateUserDetails(User userDetails);
	User loginUser(String name, String password);
	boolean isUserExists(String email);
	public void forgotPassword(String email);
	
	
}
