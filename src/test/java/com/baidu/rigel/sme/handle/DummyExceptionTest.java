package com.baidu.rigel.sme.handle;

import javax.annotation.Resource;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.baidu.rigel.sme.ApplicationBusinessException;
import com.baidu.rigel.sme.dummy.Dummy;
import com.baidu.rigel.sme.dummy.DummyException;
import com.baidu.rigel.sme.dummy.DummySingleTarget;
import com.baidu.rigel.sme.dummy.DummyTarget;

@ContextConfiguration(locations = { "/applicationContext-exception-test.xml" })
public class DummyExceptionTest extends AbstractTestNGSpringContextTests {

	@Resource(name = "dummy")
	private Dummy dummy;
	
	@Resource(name = "dummyTarget")
	private DummyTarget dummyTarget;
	
	@Resource(name = "dummySingleTarget")
	private DummySingleTarget dummySingleTarget;
	
	@Test(expectedExceptions = ApplicationBusinessException.class)
	public void wrap() {
		dummy.wrap();
	}
	
	@Test(expectedExceptions = DummyException.class)
	public void wrapBy() {
		dummy.wrapBy();
	}
	
	@Test
	public void msgDummy() {
		try{
			dummy.msgDummy();
		} catch (Exception e) {
			Assert.assertEquals(true, e instanceof ApplicationBusinessException);
			Assert.assertEquals("dummy", e.getMessage());
			Assert.assertEquals(RuntimeException.class, e.getCause().getClass());
		}
	}
	
	@Test
	public void msgSource() {
		try{
			dummy.msgSource();
		} catch (Exception e) {
			Assert.assertEquals(true, e instanceof ApplicationBusinessException);
			Assert.assertEquals(Dummy.msg, e.getMessage());
			Assert.assertEquals(RuntimeException.class, e.getCause().getClass());
		}
	}
	
	//优先使用AbstractApplicationException子类的code和params
	@Test
	public void msgByThrow() {
		try{
			dummy.msgByThrow();
		} catch (Exception e) {
			Assert.assertEquals(true, e instanceof ApplicationBusinessException);
			Assert.assertEquals("参数name不能为空", e.getMessage());
			Assert.assertEquals(DummyException.class, e.getCause().getClass());
		}
	}
	
	//其次是注解定义的
	@Test
	public void msgByThrow1() {
		try{
			dummy.msgByThrow1();
		} catch (Exception e) {
			Assert.assertEquals(true, e instanceof ApplicationBusinessException);
			Assert.assertEquals("该条记录已经存在", e.getMessage());
			Assert.assertEquals(RuntimeException.class, e.getCause().getClass());
		}
	}
	
	//其次是注解定义的,设置多余的参数
	@Test
	public void msgByThrow11() {
		try{
			dummy.msgByThrow11();
		} catch (Exception e) {
			Assert.assertEquals(true, e instanceof ApplicationBusinessException);
			Assert.assertEquals("该条记录已经存在", e.getMessage());
			Assert.assertEquals(RuntimeException.class, e.getCause().getClass());
		}
	}
	
	//其次是注解定义的,设置参数个数不符
	@Test
	public void msgByThrow12() {
		try{
			dummy.msgByThrow12();
		} catch (Exception e) {
			Assert.assertEquals(true, e instanceof ApplicationBusinessException);
			Assert.assertEquals("参数name不能为空", e.getMessage());
			Assert.assertEquals(RuntimeException.class, e.getCause().getClass());
		}
	}
	
	//在次是使用异常的原生message
	@Test
	public void msgByThrow2() {
		try{
			dummy.msgByThrow2();
		} catch (Exception e) {
			Assert.assertEquals(true, e instanceof ApplicationBusinessException);
			Assert.assertEquals("该条记录已经存在", e.getMessage());
			Assert.assertEquals(RuntimeException.class, e.getCause().getClass());
		}
	}
	
	@Test
	public void multipleExceptionProcess() {
		try{
			dummy.multipleExceptionProcess(false);
		} catch (Exception e) {
			Assert.assertEquals(true, e instanceof ApplicationBusinessException);
			Assert.assertEquals("该条记录已经存在", e.getMessage());
			Assert.assertEquals(ClassNotFoundException.class, e.getCause().getClass());
		}
		
		try{
			dummy.multipleExceptionProcess(true);
		} catch (Exception e) {
			Assert.assertEquals(true, e instanceof ApplicationBusinessException);
			Assert.assertEquals("参数name不能为空", e.getMessage());
			Assert.assertEquals(NullPointerException.class, e.getCause().getClass());
		}
	}
	
	@Test
	public void multipleExceptionProcess1() {
		try{
			dummyTarget.multipleExceptionProcess(false);
		} catch (Exception e) {
			Assert.assertEquals(true, e instanceof ApplicationBusinessException);
			Assert.assertEquals("该条记录已经存在", e.getMessage());
			Assert.assertEquals(ClassNotFoundException.class, e.getCause().getClass());
		}
		
		try{
			dummyTarget.multipleExceptionProcess(true);
		} catch (Exception e) {
			Assert.assertEquals(true, e instanceof ApplicationBusinessException);
			Assert.assertEquals("参数name不能为空", e.getMessage());
			Assert.assertEquals(NullPointerException.class, e.getCause().getClass());
		}
	}
	
	@Test
	public void multipleExceptionProcess2() {
		try{
			dummySingleTarget.multipleExceptionProcess(false);
		} catch (Exception e) {
			Assert.assertEquals(true, e instanceof ClassNotFoundException);
			Assert.assertEquals("ClassNotFound", e.getMessage());
		}
		
		try{
			dummySingleTarget.multipleExceptionProcess(true);
		} catch (Exception e) {
			Assert.assertEquals(true, e instanceof ApplicationBusinessException);
			Assert.assertEquals("参数name不能为空", e.getMessage());
			Assert.assertEquals(NullPointerException.class, e.getCause().getClass());
		}
	}
}
