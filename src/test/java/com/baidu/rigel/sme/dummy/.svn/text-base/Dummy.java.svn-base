package com.baidu.rigel.sme.dummy;

import org.springframework.stereotype.Service;

import com.baidu.rigel.sme.annotation.ExceptionProcessor;
import com.baidu.rigel.sme.annotation.ExceptionProcessors;

@Service("dummy")
public class Dummy {

	public static final String msg = "source";
	
	@ExceptionProcessor(message="test.null", substitute = RuntimeException.class)
	public void wrap() {
		throw new RuntimeException(msg);
	}
	
	@ExceptionProcessor(message="test.null", substitute = RuntimeException.class, substituteBy=DummyException.class)
	public void wrapBy() {
		throw new RuntimeException(msg);
	}
	
	@ExceptionProcessor(message="dummy", substitute = RuntimeException.class)
	public void msgDummy() {
		throw new RuntimeException(msg);
	}
	
	@ExceptionProcessor(substitute = RuntimeException.class)
	public void msgSource() {
		throw new RuntimeException(msg);
	}
	
	@ExceptionProcessor(message="test.exist", messageParams={"name"}, substitute = RuntimeException.class)
	public void msgByThrow() {
		throw new DummyException("test.null", new Object[]{"name"});
	}
	
	@ExceptionProcessor(message="test.exist", substitute = RuntimeException.class)
	public void msgByThrow1() {
		throw new RuntimeException(msg);
	}
	
	@ExceptionProcessor(message="test.exist", messageParams={"name"}, substitute = RuntimeException.class)
	public void msgByThrow11() {
		throw new RuntimeException(msg);
	}
	
	@ExceptionProcessor(message="test.null", messageParams={"name", "sex"}, substitute = RuntimeException.class)
	public void msgByThrow12() {
		throw new RuntimeException(msg);
	}
	
	@ExceptionProcessor(substitute = RuntimeException.class)
	public void msgByThrow2() {
		throw new RuntimeException("test.exist");
	}
	
	@ExceptionProcessors({
			@ExceptionProcessor(
					substitute=NullPointerException.class,
					message="test.null",
					messageParams={"name", "sex"}),
			@ExceptionProcessor(
					substitute=ClassNotFoundException.class,
					message="test.exist")
	})
	public void multipleExceptionProcess(boolean flag) throws Exception {
		if (flag) {
			throw new NullPointerException();
		} else {
			throw new ClassNotFoundException();
		}
	}

}
