package com.rockstar.product.web;

import org.springframework.hateoas.ResourceSupport;

public class ProductInfoResource extends ResourceSupport  {
	
	private String name;
	private String title;
	private String image;
	
	public ProductInfoResource() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
