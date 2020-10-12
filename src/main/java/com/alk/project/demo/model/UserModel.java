package com.alk.project.demo.model;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
public class UserModel {
	
	private String username;
	private String password;
	private ArrayList<String> role;
	
	public UserModel() {
		
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
	public ArrayList<String> getRole() {
		return role;
	}
	public void setRole(ArrayList<String> role) {
		this.role = role;
	}
	

}
