package com.baidu.rigel.sme.bo;

public class ExceptionMessageModel {

	private String message;
	
	private Object[] messageParameters;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object[] getMessageParameters() {
		return messageParameters;
	}

	public void setMessageParameters(Object[] messageParameters) {
		this.messageParameters = messageParameters;
	}
	
	public static ExceptionMessageModel createExceptionMessageModel(String code, Object[] messageParameters) {
		ExceptionMessageModel exceptionMessageModel = new ExceptionMessageModel();
		exceptionMessageModel.setMessage(code);
		exceptionMessageModel.setMessageParameters(messageParameters);
		return exceptionMessageModel;
	}
	
}
