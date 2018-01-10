package com.rockstar.product.service;

public abstract class BaseException extends RuntimeException {
	
	private static final long serialVersionUID = 2741718638609514681L;

	private String code;

	public BaseException(String code, String message) {
		super(message);
		this.code = code;
	}
	
	public String getError() {
		return this.code;
	}
	
	public String getMessage() {
		return String.format("%s.%sResource", this.code, super.getMessage());
	}
}
