package com.faresa.aplikasikecambah.pojo.login;

import com.google.gson.annotations.SerializedName;

public class TokenResponse {

	@SerializedName("token")
	private String token;

	public void setToken(String token){
		this.token = token;
	}

	public String getToken(){
		return token;
	}

	@Override
 	public String toString(){
		return 
			"TokenResponse{" +
			"token = '" + token + '\'' + 
			"}";
		}
}