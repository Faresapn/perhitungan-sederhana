package com.faresa.aplikasikecambah.pojo.register;

import com.google.gson.annotations.SerializedName;

public class Register{

	@SerializedName("user")
	private RegisterItems registerItems;

	@SerializedName("token")
	private String token;

	public void setRegisterItems(RegisterItems registerItems){
		this.registerItems = registerItems;
	}

	public RegisterItems getRegisterItems(){
		return registerItems;
	}

	public void setToken(String token){
		this.token = token;
	}

	public String getToken(){
		return token;
	}

	@Override
 	public String toString(){
		return 
			"Register{" + 
			"user = '" + registerItems + '\'' +
			",token = '" + token + '\'' + 
			"}";
		}
}