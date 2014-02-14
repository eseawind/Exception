package com.baidu.rigel.sme.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.baidu.rigel.sme.annotation.ExceptionProcessor;
import com.baidu.rigel.sme.annotation.ExceptionProcessors;
import com.baidu.rigel.sme.handle.ExceptionProcessorHelper;

@Component
@Aspect
public class ExceptionProcessAspect {

	@Around("@annotation(s)")
	public Object handleExceptionThrownByMethod(
			ProceedingJoinPoint pjp, ExceptionProcessor s) throws Throwable {
		return ExceptionProcessorHelper.processException(pjp, s);
	}

	@Around("@annotation(s)")
	public Object handleExceptionThrownsByMethod(
			ProceedingJoinPoint pjp, ExceptionProcessors s) throws Throwable {
		return ExceptionProcessorHelper.processExceptions(pjp, s);
	}
	
	@Around("execution(!@com.baidu.rigel.sme.annotation.ExceptionProcessor *(@com.baidu.rigel.sme.annotation.ExceptionProcessor *).*(..)) && @target(s)")
	public Object handleExceptionThrownByClass(
			ProceedingJoinPoint pjp, ExceptionProcessor s) throws Throwable {
		return ExceptionProcessorHelper.processException(pjp, s);
	}
	
	@Around("execution(!@com.baidu.rigel.sme.annotation.ExceptionProcessors *(@com.baidu.rigel.sme.annotation.ExceptionProcessors *).*(..)) && @target(s)")
	public Object handleExceptionThrownsByClass(
			ProceedingJoinPoint pjp, ExceptionProcessors s) throws Throwable {
		return ExceptionProcessorHelper.processExceptions(pjp, s);
	}

}