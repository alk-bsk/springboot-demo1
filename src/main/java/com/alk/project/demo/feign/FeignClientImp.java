package com.alk.project.demo.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alk.project.demo.model.Emp;

@FeignClient(value = "testJson", url = "http://localhost:8082")
public interface FeignClientImp {

	@RequestMapping(method = RequestMethod.GET, value = "/employee")
	List<Emp> getEmps(@RequestHeader("Authorization") String header);

	@RequestMapping(method = RequestMethod.GET, value = "/employee/id/{id}", produces = "application/json")
	Emp getEmpById(@PathVariable("id") int id);
}
