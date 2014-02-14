package com.baidu.rigel.sme.log;

import org.apache.log4j.Logger;

public class ExceptionLogHelper {
	
	public static void logException(Class<?> clazz, String message, Throwable e, int logVerbosity,
			int logLevel) {

		switch (logVerbosity) {
		case LogVerbosity.MESSAGE:
			logLevelProcess(clazz, message, null, logLevel);
			break;
		case LogVerbosity.FULL:
			logLevelProcess(clazz, message, e, logLevel);
			break;
		}
	}

	private static void logLevelProcess(Class<?> clazz, String msg, Throwable e, int logLevel) {
		
		Logger log = Logger.getLogger(clazz);
		
		switch (logLevel) {
		case LogLevel.DEBUG:
			log.debug(msg, e);
			break;
		case LogLevel.INFO:
			log.info(msg, e);
			break;
		case LogLevel.WARN:
			log.warn(msg, e);
			break;
		case LogLevel.ERROR:
			log.error(msg, e);
			break;
		case LogLevel.FATAL:
			log.fatal(msg, e);
			break;
		}
	}

}
