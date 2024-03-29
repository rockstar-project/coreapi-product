package com.rockstar.product.web;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.springframework.hateoas.RepresentationModel;

public class ProductResource extends RepresentationModel<ProductResource>  {
	
	private String name;
	private String title;
	private String subtitle;
	private String description;
	private String image;
	private String blogUrl;
	private String schemaUrl;
	private Boolean featured;
	private String author;
	private String version;
	private String state;
	private String price;
	private String visibility;
	private String organization;
	private LocalDateTime createdAt;
	private List<AttributeResource> attributes;
	private List<OptionResource> options;
	private List<MediaResource> mediaItems;
	
	public ProductResource() {
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
	
	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getBlogUrl() {
		return blogUrl;
	}

	public void setBlogUrl(String blogUrl) {
		this.blogUrl = blogUrl;
	}
	
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	public void setSchemaUrl(String schemaUrl) {
		this.schemaUrl = schemaUrl;
	}

	public String getSchemaUrl() {
		return schemaUrl;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
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

	public List<MediaResource> getMediaItems() {
		return mediaItems;
	}

	public void setMediaItems(List<MediaResource> mediaItems) {
		this.mediaItems = mediaItems;
	}

}
