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
	private String architecture;
	
	public ProductSearchSpecification() {
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getArchitecture() {
		return architecture;
	}

	public void setArchitecture(String architecture) {
		this.architecture = architecture;
	}

	public Boolean getFeatured() {
		return featured;
	}

	public void setFeatured(Boolean featured) {
		this.featured = featured;
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
	    if (this.getArchitecture() != null && !this.getArchitecture().isEmpty()) {
	        predicateList.add(builder.equal(root.get("architecture"), this.getArchitecture()));
	    }
	    
	    Predicate[] predicates = new Predicate[predicateList.size()];
	    predicateList.toArray(predicates);
	    
	    return builder.and(predicates);
	}

}
