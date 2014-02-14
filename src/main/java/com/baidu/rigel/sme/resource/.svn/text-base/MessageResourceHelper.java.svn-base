package com.baidu.rigel.sme.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import static com.baidu.rigel.sme.config.ExceptionConfiguration.BASENAME;;

/**
 * @author Pillar.Zhong
 */
public class MessageResourceHelper {

	protected final Logger logger = Logger.getLogger(getClass());

	private final Map<String, Map<Locale, ResourceBundle>> cachedResourceBundles = 
		new ConcurrentHashMap<String, Map<Locale, ResourceBundle>>();

	private MessageResourceHelper() {
	}
	
	private static MessageResourceHelper helper = new MessageResourceHelper();
	
	public static MessageResourceHelper getInstance() {
		return helper;
	}
	
	public ResourceBundle getResourceBundle(String basename, Locale locale) {
		Map<Locale, ResourceBundle> localeMap = (Map<Locale, ResourceBundle>) this.cachedResourceBundles
				.get(basename);
		if (localeMap != null) {
			ResourceBundle bundle = (ResourceBundle) localeMap.get(locale);
			if (bundle != null) {
				return bundle;
			}
		}
		try {
			ResourceBundle bundle = doGetBundle(basename, locale);
			if (localeMap == null) {
				localeMap = new HashMap<Locale, ResourceBundle>();
				this.cachedResourceBundles.put(basename, localeMap);
			}
			localeMap.put(locale, bundle);
			return bundle;
		} catch (MissingResourceException ex) {
			logger.warn("ResourceBundle [" + basename
					+ "] not found for MessageSource: " + ex.getMessage());
			// Assume bundle not found
			// -> do NOT throw the exception to allow for checking parent
			// message source.
			return null;
		}
	}
	
	public List<ResourceBundle> getResourceBundleChain(String basename, Locale locale) {
		List<ResourceBundle> bundles = new ArrayList<ResourceBundle>();
		if (StringUtils.isNotBlank(basename)) {
			ResourceBundle app = getResourceBundle(basename, locale);
			bundles.add(app);
		}
		bundles.add(getResourceBundle(BASENAME, locale));
		return bundles;
	}
	
	private ResourceBundle doGetBundle(String basename, Locale locale)
			throws MissingResourceException {
		return ResourceBundle.getBundle(basename, locale);
	}

}
