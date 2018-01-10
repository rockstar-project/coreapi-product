package com.rockstar.product.web;

import org.springframework.hateoas.ResourceSupport;

public class OptionResource extends ResourceSupport {
	
	private String name;
	private String value;
	private String title;
	private String image;
	private String version;
	
	public OptionResource() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
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

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
}
