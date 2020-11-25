package com.github.elhamjamshidpey.bookRecommendation.data;

import javax.persistence.Entity;
import javax.persistence.Id;

/*
@uthor by Elham
May 27, 2019
*/

@Entity
public class User {
	
	@Id
	private String username;
	private String password;
	
	public User(String username,String password) {
		this.username = username;
		this.password = password;
	}
	
	public User() {
		
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
