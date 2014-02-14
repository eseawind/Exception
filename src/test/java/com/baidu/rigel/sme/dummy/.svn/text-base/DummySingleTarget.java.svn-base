package com.baidu.rigel.sme.dummy;

import org.springframework.stereotype.Service;

import com.baidu.rigel.sme.annotation.ExceptionProcessor;

@Service("dummySingleTarget")
@ExceptionProcessor(
		substitute=NullPointerException.class,
		message="test.null",
		messageParams={"name", "sex"})
public class DummySingleTarget {

	public void multipleExceptionProcess(boolean flag) throws Exception {
		if (flag) {
			throw new NullPointerException();
		} else {
			throw new ClassNotFoundException("ClassNotFound");
		}
	}
	
}
