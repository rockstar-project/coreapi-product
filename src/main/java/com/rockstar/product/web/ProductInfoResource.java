package com.rockstar.product.web;

import java.util.List;

import org.springframework.hateoas.ResourceSupport;

public class ProductInfoResource extends ResourceSupport  {
	
	private String name;
	private String title;
	private String subtitle;
	private String image;
	private List<AttributeResource> attributes;
	
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

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<AttributeResource> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<AttributeResource> attributes) {
		this.attributes = attributes;
	}
	
}
