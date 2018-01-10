package com.rockstar.product.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="OPTION_ITEM")
public class Option {
	
	@JsonIgnore
	@Id
	@Column(name="ID")
	private String id;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="VALUE")
	private String value;
	
	@JsonIgnore
	@Column(name="PRODUCT_ID")
	private String productId;
	
	@Column(name="IMAGE")
	private String image;
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="VERSION")
	private String version;
	
	public Option() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@PrePersist
	public void assignId() {
		if (this.getId() == null || this.getId().isEmpty()) {
			this.setId(UUID.randomUUID().toString());
		}
	}
	
}

