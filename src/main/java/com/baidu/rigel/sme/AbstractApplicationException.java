package com.baidu.rigel.sme;

public abstract class AbstractApplicationException extends RuntimeException {

	private static final long serialVersionUID = -6670994097621295521L;

	private String code;
	
	private Object[] messageParams;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Object[] getMessageParams() {
		return messageParams;
	}

	public void setMessageParams(Object[] messageParams) {
		this.messageParams = messageParams;
	}
	
	public AbstractApplicationException() {
	}
	
	public AbstractApplicationException(String msg) {
		super(msg);
		setCode(msg);
	}
	
	public AbstractApplicationException(String msg, Object[] messageParams) {
		super(msg);
		setCode(msg);
		setMessageParams(messageParams);
	}

	public AbstractApplicationException(String msg, Object[] messageParams, Throwable throwable) {
		super(msg, throwable);
		setCode(msg);
		setMessageParams(messageParams);
	}

	public AbstractApplicationException(String msg, Throwable throwable) {
		super(msg, throwable);
		setCode(msg);
	}
	
	public AbstractApplicationException(Throwable throwable) {
		super(throwable);
	}
	
}
