package com.faresa.aplikasikecambah.pojo;

public class UserResponse{
	private User user;

	public void setUser(User user){
		this.user = user;
	}

	public User getUser(){
		return user;
	}

	@Override
 	public String toString(){
		return 
			"UserResponse{" + 
			"user = '" + user + '\'' + 
			"}";
		}
}
