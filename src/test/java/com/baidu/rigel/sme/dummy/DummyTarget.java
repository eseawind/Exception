package com.baidu.rigel.sme.dummy;

import org.springframework.stereotype.Service;

import com.baidu.rigel.sme.annotation.ExceptionProcessor;
import com.baidu.rigel.sme.annotation.ExceptionProcessors;

@Service("dummyTarget")
@ExceptionProcessors({
			@ExceptionProcessor(
					substitute=NullPointerException.class,
					message="test.null",
					messageParams={"name", "sex"}),
			@ExceptionProcessor(
					substitute=ClassNotFoundException.class,
					message="test.exist")
	})
public class DummyTarget {

	public void multipleExceptionProcess(boolean flag) throws Exception {
		if (flag) {
			throw new NullPointerException();
		} else {
			throw new ClassNotFoundException();
		}
	}
	
}
