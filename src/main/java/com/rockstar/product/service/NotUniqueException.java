package com.rockstar.product.service;

public class NotUniqueException extends BaseException {

	private static final long serialVersionUID = 1825806169309983784L;
	
	public NotUniqueException(String name) {
		super("Duplicate", name);
	}

}
