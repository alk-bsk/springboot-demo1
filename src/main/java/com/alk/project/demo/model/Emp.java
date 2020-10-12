package com.alk.project.demo.model;

import org.springframework.stereotype.Component;

@Component
public class Emp {
	
	private String firstName;

	public Emp() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Emp(String firstName) {
		super();
		this.firstName = firstName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public String toString() {
		return "Emp [firstName=" + firstName + "]";
	}
	
	
}
