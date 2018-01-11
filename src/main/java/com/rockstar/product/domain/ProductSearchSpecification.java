package com.rockstar.product.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class ProductSearchSpecification implements Specification<Product> {
	
	private String keyword;
	private Boolean featured;
	private String status;
	private String author;
	private String organization;
	
	public ProductSearchSpecification() {
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Boolean getFeatured() {
		return featured;
	}

	public void setFeatured(Boolean featured) {
		this.featured = featured;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		List<Predicate> predicateList = null;
	 
	    predicateList = new ArrayList<Predicate>();
	    
	    if (this.getKeyword() != null && !this.getKeyword().isEmpty()) {
	        predicateList.add(builder.like(root.get("title"), "%" + this.getKeyword() + "%"));
	    }
	    if (this.getFeatured() != null) {
	        predicateList.add(builder.equal(root.get("featured"), this.getFeatured()));
	    }
	    if (this.getAuthor() != null && !this.getAuthor().isEmpty()) {
	        predicateList.add(builder.equal(root.get("author"), this.getAuthor()));
	    }
	    if (this.getStatus() != null && !this.getStatus().isEmpty()) {
	        predicateList.add(builder.equal(root.get("status"), this.getStatus()));
	    }
	    if (this.getOrganization() != null) {
	        predicateList.add(builder.equal(root.get("organization"), this.getOrganization()));
	    }
	    
	    Predicate[] predicates = new Predicate[predicateList.size()];
	    predicateList.toArray(predicates);
	    
	    return builder.and(predicates);
	}

}
