package com.baidu.rigel.sme.config;

import java.util.ArrayList;
import java.util.List;

public class ModuleConfig {
	
	public static final List<String> configs = new ArrayList<String>();
	
	static {
		configs.add(ConfigMapping.DEFAULT_BASENAME);
		configs.add(ConfigMapping.APP_BASENAME);
		configs.add(ConfigMapping.LANGUAGE);
		configs.add(ConfigMapping.REGION);
	}
	
	private String baseName;
	
	private String language;
	
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getBaseName() {
		return baseName;
	}

	public void setBaseName(String baseName) {
		this.baseName = baseName;
	}

}
