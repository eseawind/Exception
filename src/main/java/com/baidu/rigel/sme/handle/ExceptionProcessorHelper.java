package com.baidu.rigel.sme.handle;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;

import com.baidu.rigel.sme.AbstractApplicationException;
import com.baidu.rigel.sme.annotation.ExceptionProcessor;
import com.baidu.rigel.sme.annotation.ExceptionProcessors;
import com.baidu.rigel.sme.bo.ExceptionMessageModel;
import com.baidu.rigel.sme.log.ExceptionLogHelper;
import com.baidu.rigel.sme.message.MessageLoader;

public class ExceptionProcessorHelper {

	private static final Logger logger = Logger.getLogger(ExceptionProcessorHelper.class);
	
	/**
	 * Handles exceptions by rules  defined in ExceptionProcessor
	 */
	public static Object processExceptions(ProceedingJoinPoint pjp, ExceptionProcessors eps) throws Throwable {
		try {
			return pjp.proceed();
		} catch (Throwable e) {
			ExceptionProcessor[] processors = eps.value();
			ExceptionProcessor processor = searchProcessorByExceptionType(processors, e);
			String msg = messageProcess(pjp, processor, e);
			handlerProcess(pjp, processor, e);
			substituteProcess(processor, e, msg);
			throw e;
		}
	}
	
	/**
	 * Handles exceptions by rules  defined in ExceptionProcessor
	 */
	public static Object processException(ProceedingJoinPoint pjp, ExceptionProcessor processor) throws Throwable {
		try {
			return pjp.proceed();
		} catch (Throwable e) {
			Class substitute = getSubstitute(processor);
			String msg = messageProcess(pjp, processor, e);
			if (null != substitute && isMatch(e, substitute)) {
				handlerProcess(pjp, processor, e);
				substituteProcess(processor, e, msg);
			}
			throw e;
		}
	}

	private static void handlerProcess(ProceedingJoinPoint pjp, ExceptionProcessor ep, Throwable e) throws Throwable {
		if (ep.handler() != null && !(ep.handler().equals(SkipExceptionHandler.class))) {
			try {
				ExceptionHandler exceptionHandler =(ExceptionHandler) ep.handler().newInstance();
				try {
					exceptionHandler.handle(pjp, e);
				}  finally {
					exceptionHandler.finallyHandle(pjp, e);
				}
			} catch(NoSuchMethodException ex) {
				logger.error("The implementation class of handler must have getConstructor().", ex);
				return;
			}
		}
	}

	private static String messageProcess(ProceedingJoinPoint pjp,
			ExceptionProcessor ep, Throwable e) {
		ExceptionMessageModel model = getExceptionMessageModel(e, ep);
		MessageLoader messageLoader = MessageLoader.getInstance();
		String msg = messageLoader.getMessage(model.getMessage(), model.getMessageParameters());
		ExceptionLogHelper.logException(pjp.getSignature().getDeclaringType(), msg, e, ep.logVerbosity(), ep.logLevel());
		return msg;
	}
	
	private static boolean isMatch(Throwable e, Class<? extends Throwable> exceptionClass) {
		Class<? extends Throwable> thrownClass = e.getClass();
		if (exceptionClass.isAssignableFrom(thrownClass) || exceptionClass.equals(thrownClass)) {
			return true;
		}
		return false;
	}
	
	private static void substituteProcess(ExceptionProcessor ep, Throwable e, String message) throws Throwable  {
		Class<? extends Throwable> replaceException = ep.substitute();
		Class<? extends Throwable> replaceByException = ep.substituteBy();
		if (replaceException != null && replaceByException != null) {
			if (isMatch(e, replaceException)) {
				throw createException(replaceByException, e, message);
			}
		}
		
		if (replaceException == null && replaceByException != null) {
			logger.error("The substitute cant be null when set a substituteBy.");
		}
		throw e;
	}
	
	private static Throwable createException(Class<? extends Throwable> c, Throwable exceptionSource, String message) {
		try {
			return c.getConstructor(String.class, Throwable.class).newInstance(message, exceptionSource);
		} catch (NoSuchMethodException e) {
			logger.error("The implementation class of substituteBy must have getConstructor(String.class, Throwable.class).", e);
			try {
				return c.getConstructor(Throwable.class).newInstance(exceptionSource);
			} catch (Exception e1) {
				logger.error("The implementation class of substituteBy must have getConstructor(Throwable.class).", e1);
				return exceptionSource;
			}
		} catch (Throwable e) {
			logger.error("Oops! Something happen.", e);
			return exceptionSource;
		}
	}
	
	private static ExceptionProcessor searchProcessorByExceptionType(ExceptionProcessor[] processors, Throwable t) {
		for (ExceptionProcessor exceptionProcessor : processors) {
			Class substitute = getSubstitute(exceptionProcessor);
			if (null != substitute && isMatch(t, substitute)) {
				return exceptionProcessor;
			}
		}
		return null;
	}
	
	private static Class<? extends Throwable> getSubstitute(ExceptionProcessor exceptionProcessor) {
		Class substitute = exceptionProcessor.substitute();
		if (null != substitute) {
			return substitute;
		} else {
			logger.error("You need to set the substitute exception.");
			return null; 
		}
	}
	
	private static ExceptionMessageModel getExceptionMessageModel(Throwable t, ExceptionProcessor processor) {
		String code = "";
		Object[] messageParams = null; 
		if (AbstractApplicationException.class.isAssignableFrom(t.getClass())) {
			code = ((AbstractApplicationException)t).getCode();
			messageParams = ((AbstractApplicationException)t).getMessageParams();
		} else {
			String msg = processor.message();
			code = StringUtils.isNotEmpty(msg) ? msg : t.getMessage();
			messageParams = processor.messageParams();
		}
		return ExceptionMessageModel.createExceptionMessageModel(code, messageParams);
	}
	
}
