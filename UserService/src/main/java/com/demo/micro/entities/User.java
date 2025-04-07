package com.demo.micro.entities;

import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor


@Document(collection="micro_users")
public class User {
	
	@Id
	private String userId;

	private String name;

	private String email;

	private String password;


	public String getUserId() {
		return userId;
	}

	public void setUserID(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String about) {
		this.password = about;
	}
	
	
}
