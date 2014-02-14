package com.baidu.rigel.sme.handle;

import org.aspectj.lang.ProceedingJoinPoint;

public class DoNothingExceptionHandler implements ExceptionHandler {

	public boolean handle(ProceedingJoinPoint pjp, Throwable e)
			throws Throwable {
		return true;
	}

	public boolean finallyHandle(ProceedingJoinPoint pjp, Throwable e) {
		// TODO Auto-generated method stub
		return true;
	}
}
