package com.rockstar.product.web;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import org.springframework.hateoas.ResourceSupport;

public class ProductDetailsResource extends ResourceSupport  {
	
	private String name;
	private String title;
	private String subtitle;
	private String description;
	private String image;
	private Boolean featured;
	private String author;
	private String version;
	private String status;
	private String visibility;
	private String organization;
	private DateTime createdAt;
	private List<AttributeResource> attributes;
	private List<OptionResource> options;
	
	public ProductDetailsResource() {
	}

	@NotEmpty
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@NotEmpty
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@NotEmpty
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@NotEmpty
	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public Boolean getFeatured() {
		return featured;
	}

	public void setFeatured(Boolean featured) {
		this.featured = featured;
	}
	
	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	public List<AttributeResource> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<AttributeResource> attributes) {
		this.attributes = attributes;
	}

	@NotEmpty
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public DateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(DateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public List<OptionResource> getOptions() {
		return options;
	}

	public void setOptions(List<OptionResource> options) {
		this.options = options;
	}

}
