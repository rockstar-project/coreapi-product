package com.rockstar.product.service;

public class NotFoundException extends BaseException {

	private static final long serialVersionUID = 1825806169309983784L;
	
	public NotFoundException(String name) {
		super("NotFound", name);
	}
}
