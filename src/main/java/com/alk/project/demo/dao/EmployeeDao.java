package com.alk.project.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alk.project.demo.entity.Employee;

@Repository
public interface EmployeeDao extends JpaRepository<Employee, Integer> {
	public List<Employee> findByFirstName(String firstName);
}
