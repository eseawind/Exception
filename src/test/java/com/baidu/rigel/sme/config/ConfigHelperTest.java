package com.baidu.rigel.sme.config;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ConfigHelperTest {

  @Test
  public void get() {
	  
	  String value = ConfigHelper.get("exceptionconfig.properties", "exception.module.basename");
	  
	  Assert.assertNull(value);
	  
	  Assert.assertEquals("exception", ExceptionConfiguration.BASENAME);
  }
}
