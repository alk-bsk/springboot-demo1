package com.alk.project.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alk.project.demo.dao.EmployeeDao;
import com.alk.project.demo.entity.Employee;
import com.alk.project.demo.exception.EmployeeNotFound;

@Service
public class EmployeeServiceImp implements EmployeeService {
	
	@Autowired
	EmployeeDao employeeDao;

	@Override
	@Transactional
	public List<Employee> getEmployees() {
		// TODO Auto-generated method stub
		return employeeDao.findAll();
	}

	@Override
	@Transactional
	public Employee registerEmployee(Employee em) {
		// TODO Auto-generated method stub
		return employeeDao.saveAndFlush(em);
	}

	@Override
	@Transactional
	public Optional<Employee> getEmployeeById(int id) {
		// TODO Auto-generated method stub
		return employeeDao.findById(id);
	}

	@Override
	@Transactional
	public List<Employee> getEmployeeByName(String name) {
		// TODO Auto-generated method stub
		List<Employee> empList=employeeDao.findByFirstName(name);
		return empList;
	}

	@Override
	@Transactional
	public void deleteEmployee(int id) {
		// TODO Auto-generated method stub
		if(!getEmployeeById(id).isPresent()) {
			throw new EmployeeNotFound("Employee not exist id : "+id);
		}
		employeeDao.deleteById(id);
	}

}
