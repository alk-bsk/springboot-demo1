package com.alk.project.demo.jwt;

import java.io.Serializable;

public class JwtRequest implements Serializable {

	private static final long serialVersionUID = 2811562436444361876L;
	
	private String username;
	private String password;
	
	public JwtRequest() {
	}

	public JwtRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
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