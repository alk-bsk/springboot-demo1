package com.alk.project.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alk.project.demo.feign.FeignClientImp;
import com.alk.project.demo.model.Emp;

@RestController
public class FeignUserController {

	@Autowired
	FeignClientImp feignClientimp;

	@RequestMapping("/emps")
	public ResponseEntity<List<Emp>> getPost(@RequestHeader("Authorization") String header) {
		List<Emp> post = feignClientimp.getEmps(header);
		// RestTemplate restTemplte=new RestTemplate();
		// List<Employee> post=
		// restTemplte.exchange("http://localhost:8082/employee",HttpMethod.GET, null,
		// new ParameterizedTypeReference<List<Employee>>() {} ).getBody();
		return new ResponseEntity<List<Emp>>(post, HttpStatus.OK);
	}

	@RequestMapping("/emps/{id}")
	public ResponseEntity<Emp> getPostById(@PathVariable("id") int id) {
		Emp emp = feignClientimp.getEmpById(id);
		return new ResponseEntity<Emp>(emp, HttpStatus.OK);
	}

}
