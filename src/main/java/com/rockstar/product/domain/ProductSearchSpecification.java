package com.rockstar.product.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class ProductSearchSpecification implements Specification<Product> {
	
	private String query;
	private Boolean featured;
	private String visibility;
	private String state;
	private String author;
	private String organization;
	private String price;
	
	public ProductSearchSpecification() {
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public Boolean getFeatured() {
		return featured;
	}

	public void setFeatured(Boolean featured) {
		this.featured = featured;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
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
	
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		List<Predicate> predicateList = null;
	 
	    predicateList = new ArrayList<Predicate>();
	    
	    if (this.getQuery() != null && !this.getQuery().isEmpty()) {
	        predicateList.add(builder.like(builder.lower(root.<String>get("title")), "%" + this.getQuery() + "%"));
	    }
	    if (this.getFeatured() != null) {
	        predicateList.add(builder.equal(root.get("featured"), this.getFeatured()));
	    }
	    if (this.getVisibility() != null && !this.getVisibility().isEmpty()) {
	        predicateList.add(builder.equal(root.get("visibility"), this.getVisibility()));
	    }
	    if (this.getAuthor() != null && !this.getAuthor().isEmpty()) {
	        predicateList.add(builder.equal(root.get("author"), this.getAuthor()));
	    }
	    if (this.getState() != null && !this.getState().isEmpty()) {
	        predicateList.add(builder.equal(root.get("state"), this.getState()));
	    }
	    if (this.getPrice() != null && !this.getPrice().isEmpty()) {
	        predicateList.add(builder.equal(root.get("price"), this.getPrice()));
	    }
	    if (this.getOrganization() != null) {
	        predicateList.add(builder.equal(root.get("organization"), this.getOrganization()));
	    }
	    
	    Predicate[] predicates = new Predicate[predicateList.size()];
	    predicateList.toArray(predicates);
	    
	    return builder.and(predicates);
	}

}
