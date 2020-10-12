package com.alk.project.demo.service;

import java.util.List;
import java.util.Optional;

import com.alk.project.demo.entity.Employee;

public interface EmployeeService {
	public List<Employee> getEmployees();
	public Employee registerEmployee(Employee em);
	public Optional<Employee> getEmployeeById(int id);
	public List<Employee> getEmployeeByName(String name);
	public void deleteEmployee(int id);
}
