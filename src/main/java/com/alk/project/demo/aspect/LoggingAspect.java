package com.alk.project.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@AfterReturning(value = "execution(* com.alk.project.demo.controller.EmployeeController.*(..))", returning = "result")
	public void AfterControllerLog(JoinPoint jointPoint, Object result) {
		String method = jointPoint.getSignature().toShortString();

		logger.info("===>>> Controller method : " + method);
		logger.info("===>>> Controller Return : " + result);
	}

	@AfterReturning(value = "execution(* com.alk.project.demo.service.EmployeeService.*(..))", returning = "result")
	public void AfterServiceLog(JoinPoint jointPoint, Object result) {
		String method = jointPoint.getSignature().toShortString();

		logger.info("===>>> Service method : " + method);
		logger.info("===>>> Service Return : " + result);
	}

	@AfterReturning(value = "execution(* com.alk.project.demo.dao.EmployeeDao.*(..))", returning = "result")
	public void AfterDaoLog(JoinPoint jointPoint, Object result) {
		String method = jointPoint.getSignature().toShortString();

		logger.info("===>>> Dao method : " + method);
		logger.info("===>>> Dao Return : " + result);
	}

	@AfterReturning(value = "execution(* com.alk.project.demo.exception.EmployeeControllerAdvice.*(..))", returning = "result")
	public void AfterExceptionLog(JoinPoint jointPoint, Object result) {
		String method = jointPoint.getSignature().toShortString();

		logger.info("===>>> Advice method : " + method);
		logger.info("===>>> Advice Return : " + result);
	}
}
