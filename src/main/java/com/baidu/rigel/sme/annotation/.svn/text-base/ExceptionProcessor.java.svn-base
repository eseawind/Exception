package com.baidu.rigel.sme.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.baidu.rigel.sme.ApplicationBusinessException;
import com.baidu.rigel.sme.handle.SkipExceptionHandler;
import com.baidu.rigel.sme.log.LogLevel;
import com.baidu.rigel.sme.log.LogVerbosity;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface ExceptionProcessor {

	Class handler() default SkipExceptionHandler.class;
	
	/**
	 * If message is not set up the message from thrown exception will be inserted if possible. 
	 */
	String message() default "";
	
	/**
	 * message parameters 
	 */
	String[] messageParams() default {};

	/**
	 * @return LogVerbosity
	 */
	int logVerbosity() default LogVerbosity.FULL;

	/**
	 * @return LogLevel
	 */
	int logLevel() default LogLevel.ERROR;
	
	/**
	 * exception replacement
	 */
	Class substitute();
	
	/**
	 * replace exception by
	 */
	Class substituteBy() default ApplicationBusinessException.class;
	
}
