package com.alk.project.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@ControllerAdvice
public class EmployeeControllerAdvice{
	
	@ExceptionHandler
	public ResponseEntity<EmployeeExceptionHandler> handleException(Exception ex){
		EmployeeExceptionHandler eeh=new EmployeeExceptionHandler();
		eeh.setStatus(HttpStatus.BAD_REQUEST.value());
		eeh.setMessage(ex.getMessage());
		eeh.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(eeh,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public ResponseEntity<EmployeeExceptionHandler> handleException(EmployeeNotFound emp){
		EmployeeExceptionHandler eeh=new EmployeeExceptionHandler();
		eeh.setStatus(HttpStatus.NOT_FOUND.value());
		eeh.setMessage(emp.getMessage());
		eeh.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(eeh,HttpStatus.NOT_FOUND);
	}

}
