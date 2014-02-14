package com.baidu.rigel.sme.handle;

import org.aspectj.lang.ProceedingJoinPoint;

public interface ExceptionHandler {

	/**
	 * If the method throws an exception the rest of exception handling process is skipped.
	 * If method doesn't throws an exception the exception handling process will continue.
	 */
	public boolean handle(ProceedingJoinPoint pjp, Throwable e) throws Throwable;
	
	/**
	 * exception finally handler
	 */
	public boolean finallyHandle(ProceedingJoinPoint pjp, Throwable e) throws Throwable;
	
}
