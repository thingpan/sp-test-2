package com.sp.file.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class TestAOP {
	@Pointcut("execution(* com.sp.file..*Controller.*(..))")
	public void controllerCut() {};
	
	@Before("controllerCut()")
	public void beforeController(JoinPoint jp) {
		log.info("before=>{}",jp.getSignature().getName());
	}

	@After("controllerCut()")
	public void afterController(JoinPoint jp) {
		log.info("after=>{}",jp.getSignature().getName());
	}
	
	@Around("controllerCut()")
	public Object aroundController(ProceedingJoinPoint pjp) throws Throwable {
		log.info("around before=>{}",pjp.getSignature().getName());
		Object obj =pjp.proceed();
		log.info("around after=>{}",pjp.getSignature().getName());
		return obj;
	}
}
