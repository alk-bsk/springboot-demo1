package com.alk.project.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alk.project.demo.entity.Employee;
import com.alk.project.demo.exception.EmployeeNotFound;
import com.alk.project.demo.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeService empService;

	@GetMapping("/")
	public ResponseEntity<String> welcome() {
		return new ResponseEntity<String>("Welcome to Server", HttpStatus.OK);
	}

	@GetMapping("/employee")
	public ResponseEntity<List<Employee>> getEmployee() {
		return new ResponseEntity<List<Employee>>(empService.getEmployees(), HttpStatus.OK);
	}

	@PostMapping("/employee")
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee emp) {
		return new ResponseEntity<Employee>(empService.registerEmployee(emp), HttpStatus.CREATED);
	}

	@PutMapping("/employee")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee emp) {
		return new ResponseEntity<Employee>(empService.registerEmployee(emp), HttpStatus.OK);
	}

	@GetMapping("/employee/id/{id}")
	public ResponseEntity<Optional<Employee>> findByIdEmployee(@PathVariable("id") int id) {
		Optional<Employee> emp = empService.getEmployeeById(id);
		if (id < 0 || !emp.isPresent()) {
			throw new EmployeeNotFound("Employee Not Found of id " + id);
		}
		return new ResponseEntity<Optional<Employee>>(emp, HttpStatus.OK);
	}

	@GetMapping("/employee/name/{name}")
	public ResponseEntity<List<Employee>> findBynameEmployee(@PathVariable("name") String name) {
		List<Employee> emp = empService.getEmployeeByName(name);
		if (emp.isEmpty()) {
			throw new EmployeeNotFound("Employee Not Found of name " + name);
		}
		return new ResponseEntity<List<Employee>>(emp, HttpStatus.OK);
	}

	@DeleteMapping("/employee/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("id") int id) {
		empService.deleteEmployee(id);
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}

}
