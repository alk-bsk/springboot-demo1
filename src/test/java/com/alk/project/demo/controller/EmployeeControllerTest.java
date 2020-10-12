package com.alk.project.demo.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.alk.project.demo.entity.Employee;
import com.alk.project.demo.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
class EmployeeControllerTest {

	@Autowired
	private MockMvc mockMVC;

	@InjectMocks
	EmployeeController empController;

	@MockBean
	EmployeeService empService;

	@Autowired
	ObjectMapper objectMapper;

	Employee emp;
	Employee emp1;

	@BeforeEach
	public void createRecord() {
		emp = new Employee();
		emp.setId(1);
		emp.setFirstName("Test1");
		emp.setLastName("Test1");

		emp1 = new Employee();
		emp1.setId(2);
		emp1.setFirstName("Test2");
		emp1.setLastName("Test2");
	}

	@Test
	@DisplayName("Find all Employee")
	public void getEmployeeTest() throws Exception {
		ArrayList<Employee> empList = new ArrayList<Employee>();
		empList.add(emp);
		empList.add(emp1);
		when(empService.getEmployees()).thenReturn(empList);

		mockMVC.perform(get("/employee").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(2));
	}

	@Test
	@DisplayName("Find Employee By Id")
	public void findemployeeByIdTest() throws Exception {

		when(empService.getEmployeeById(1)).thenReturn(Optional.of(emp));
		mockMVC.perform(get("/employee/id/1").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(emp.getId())))
				.andExpect(jsonPath("$.firstName", is(emp.getFirstName())));

	}

	@Test
	@DisplayName("Find Employee By Name")
	public void findemployeeByNameTest() throws Exception {
		ArrayList<Employee> empList = new ArrayList<Employee>();
		empList.add(emp);
		when(empService.getEmployeeByName("Test1")).thenReturn(empList);
		mockMVC.perform(get("/employee/name/Test1").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.[0].id", is(emp.getId())))
				.andExpect(jsonPath("$.[0].firstName", is(emp.getFirstName())));

	}

	@Test
	@DisplayName("Create Employee")
	public void createEmployeeTest() throws Exception {
		emp = new Employee();
		emp.setFirstName("hello");
		emp.setLastName("Test1");
		emp.setId(0);
		when(empService.registerEmployee(emp)).thenReturn(emp);
		mockMVC.perform(
				post("/employee").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(emp)))
				.andExpect(status().isCreated());

	}

	@Test
	@DisplayName("update Employee")
	public void UpdateEmployeeTest() throws Exception {
		emp = new Employee();
		emp.setId(1);
		emp.setFirstName("hello");
		emp.setLastName("Test1");

		when(empService.registerEmployee(emp)).thenReturn(emp);
		mockMVC.perform(
				put("/employee").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(emp)))
				.andExpect(status().isOk());

	}

	@Test
	@DisplayName("Delete Employee")
	public void deleteEmployeeTest() throws Exception {

		mockMVC.perform(delete("/employee/1")).andExpect(status().isOk());

	}

}
