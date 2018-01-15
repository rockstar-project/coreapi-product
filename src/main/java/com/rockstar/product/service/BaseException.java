package com.rockstar.product.service;

public abstract class BaseException extends RuntimeException {
	
	private static final long serialVersionUID = 2741718638609514681L;

	private String name;

	public BaseException(String message, String name) {
		super(message);
		this.name = name;
	}
	
	public String getMessage() {
		return String.format("%s %s", this.name, super.getMessage());
	}
}
