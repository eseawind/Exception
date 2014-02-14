package com.baidu.rigel.sme.config;

public class ExceptionConfiguration {

	public static String BASENAME = ConfigHelper.getModuleConfigByKey(ConfigMapping.DEFAULT_BASENAME) == null ? "exception" : 
		ConfigHelper.getModuleConfigByKey(ConfigMapping.DEFAULT_BASENAME);
	
	public static String APP_BASENAME = ConfigHelper.getModuleConfigByKey(ConfigMapping.APP_BASENAME);
	
	public static String APP_LANGUAGE = ConfigHelper.getModuleConfigByKey(ConfigMapping.LANGUAGE);
	
	public static String APP_REGION = ConfigHelper.getModuleConfigByKey(ConfigMapping.REGION);
	
}
