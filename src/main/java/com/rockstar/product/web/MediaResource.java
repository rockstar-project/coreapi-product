package com.rockstar.product.web;

import java.util.Collection;

import org.springframework.hateoas.ResourceSupport;

public class MediaResource extends ResourceSupport {
	
	private String title;
	private String src;
	private String thumbnail;
	private String type;
	private Collection<String> tags;
	
	public MediaResource() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}
	
	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Collection<String> getTags() {
		return tags;
	}

	public void setTags(Collection<String> tags) {
		this.tags = tags;
	}
}
