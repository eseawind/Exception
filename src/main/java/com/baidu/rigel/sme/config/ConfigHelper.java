package com.baidu.rigel.sme.config;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class ConfigHelper {

	private static Map<String, Object> configMap = new ConcurrentHashMap<String, Object>();
	
	private static String moduleConfigPath = "exceptionconfig.properties";
	
	private static final Logger logger = Logger.getLogger(ConfigHelper.class);
	
	/** 
     * 载入配置文件，初始化后加入map 
     * @param configFile 
     */  
    private static void initConfig(String configFile) {      
        try {  
            PropertiesConfiguration config = new PropertiesConfiguration(configFile);  
            configMap.put(configFile, config);  
              
        } catch (ConfigurationException e) {  
            e.printStackTrace();  
        }  
    }     
    
    /** 
     * 获取内容 
     * @param configFile 
     * @param property 
     * @return 
     */  
    public static String get(String configFile, String property) {     
        if(!configMap.containsKey(configFile)) {     
        	initConfig(configFile);  
        }  
        PropertiesConfiguration config = (PropertiesConfiguration) configMap.get(configFile);  
        String value = config.getString(property);  
        return value;     
    }     
    
    public static String getModuleConfigByKey(String key) {
		String value = null;
		if (StringUtils.isNotBlank(key)) {
			try {
				value = get(moduleConfigPath, key);
			} catch (Exception e) {
				logger.error("get property:" + key + " failed.");
			}
		}
		return value;
	}
	
}
