package com.baidu.rigel.sme.message;

import java.text.MessageFormat;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.baidu.rigel.sme.config.ExceptionConfiguration;
import com.baidu.rigel.sme.resource.MessageResourceHelper;

import static com.baidu.rigel.sme.config.ExceptionConfiguration.APP_LANGUAGE;
import static com.baidu.rigel.sme.config.ExceptionConfiguration.APP_REGION;

/**
 * @author Pillar.Zhong
 */
public class MessageLoader implements IMessageLoader {

	private static final Logger logger = Logger.getLogger(MessageLoader.class);
	
	private static MessageLoader messageLoader = new MessageLoader();
	
	private MessageLoader() {
		
	}
	
	public static MessageLoader getInstance(){
		return messageLoader;
	}
	
	private MessageResourceHelper messageResourceHelper = MessageResourceHelper.getInstance();
	
	public String getMessage(String code) {
		return getMessage(code, null, null);
	}

	public String getMessage(String code, Object[] args) {
		return getMessage(code, args, null);
	}
	
	public String getMessage(String code, Locale locale) {
		return getMessage(code, null, locale);
	}

	public String getMessage(String code, Object[] args, Locale locale) {
		
		String value = code;

		if (checkMessageConfig(code, args)) {
			
			locale = getLocale(locale);
			
			List<ResourceBundle> bundles = messageResourceHelper.getResourceBundleChain(ExceptionConfiguration.APP_BASENAME, locale);
			
			for (ResourceBundle resourceBundle : bundles) {
				if (null != resourceBundle) {
					if (resourceBundle.containsKey(code)) {
						value = resourceBundle.getString(code);
						if (!ArrayUtils.isEmpty(args)) {
							return value = MessageFormat.format(value, args);
						}
					}
				}
			}
		}
		return value;
	}
	
	public String getMessage(String code, Object[] args, Locale locale, String defaultMessage) {
		String value = getMessage(code, args, locale);
		if (StringUtils.isEmpty(value)) {
			value = defaultMessage;
		}
		return value;
	}
	
	private boolean checkMessageConfig(String code, Object[] args) {
		if (!ArrayUtils.isEmpty(args)) {
			if (StringUtils.isEmpty(code)) {
				logger.error("The messageParams cant not be empty when set the message." );
				return false;
			}
		}
		return true;
	}
	
	private Locale getLocale(Locale locale) {
		if (locale == null) {
			locale = new Locale(APP_LANGUAGE, APP_REGION);
		}
		return locale;
	}

}
