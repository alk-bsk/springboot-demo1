package com.alk.project.demo.exception;

public class EmployeeNotFound extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EmployeeNotFound(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public EmployeeNotFound(String arg0) {
		super(arg0);
	}

	public EmployeeNotFound(Throwable arg0) {
		super(arg0);
	}

}
