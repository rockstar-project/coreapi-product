package com.rockstar.product.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="MEDIA_ITEM")
public class Media {

	@JsonIgnore
	@Id
	@Column(name="ID")
	private String id;
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="SRC")
	private String src;
	
	@Column(name="THUMBNAIL")
	private String thumbnail;
	
	@Column(name="MEDIA_TYPE")
	private String type;
	
	@Column(name="TAGS")
	private String tags;
	
	@JsonIgnore
	@Column(name="PRODUCT_ID")
	private String productId;
	
	public Media() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	@PrePersist
	public void assignId() {
		if (this.getId() == null || this.getId().isEmpty()) {
			this.setId(UUID.randomUUID().toString());
		}
	}
}
