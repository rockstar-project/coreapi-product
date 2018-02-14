package com.rockstar.product.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class ProductSearchSpecification implements Specification<Product> {
	
	private String query;
	private Boolean featured;
	private String visibility;
	private String state;
	private String author;
	private String organization;
	private String price;
	private String architecture;
	private String language;
	private String framework;
	private String specification;
	
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

	public String getArchitecture() {
		return architecture;
	}

	public void setArchitecture(String architecture) {
		this.architecture = architecture;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getFramework() {
		return framework;
	}

	public void setFramework(String framework) {
		this.framework = framework;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		List<Predicate> predicateList = null;
	 
	    predicateList = new ArrayList<Predicate>();
	    
	    if (!StringUtils.isEmpty(this.query)) {
	        predicateList.add(builder.like(builder.lower(root.<String>get("title")), "%" + this.getQuery() + "%"));
	    }
	    if (this.getFeatured() != null) {
	        predicateList.add(builder.equal(root.get("featured"), this.getFeatured()));
	    }
	    if (!StringUtils.isEmpty(this.visibility)) {
	        predicateList.add(builder.equal(root.get("visibility"), this.getVisibility()));
	    }
	    if (!StringUtils.isEmpty(this.author)) {
	        predicateList.add(builder.equal(root.get("author"), this.getAuthor()));
	    }
	    if (!StringUtils.isEmpty(this.state)) {
	        predicateList.add(builder.equal(root.get("state"), this.getState()));
	    }
	    if (!StringUtils.isEmpty(this.price)) {
	        predicateList.add(builder.equal(root.get("price"), this.getPrice()));
	    }
	    if (!StringUtils.isEmpty(this.organization)) {
	        predicateList.add(builder.equal(root.get("organization"), this.getOrganization()));
	    }
	    if (!StringUtils.isEmpty(this.architecture)) {
	    	predicateList.add(this.toAttributePredicate("architecture", this.architecture, root, query, builder));
	    }
	    if (!StringUtils.isEmpty(this.language)) {
	        predicateList.add(this.toAttributePredicate("language", this.language, root, query, builder));
	    }
	    if (!StringUtils.isEmpty(this.framework)) {
	    		predicateList.add(this.toAttributePredicate("framework", this.framework, root, query, builder));
	    }
	    if (!StringUtils.isEmpty(this.specification)) {
	    		predicateList.add(this.toAttributePredicate("specification", this.specification, root, query, builder));
	    }
	    
	    Predicate[] predicates = new Predicate[predicateList.size()];
	    predicateList.toArray(predicates);
	    
	    return builder.and(predicates);
	}
	
	Predicate toAttributePredicate(String name, String value, Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		List<Predicate> predicateList = null;
		 
	    predicateList = new ArrayList<Predicate>();
	    
	    Join<Product, Attribute> productAttributes = root.join("attributes");
    		Expression<String> attributeNameExpression = productAttributes.get("name");
    		Expression<String> attributeValueExpression = productAttributes.get("value");
	    
	    if (!StringUtils.isEmpty(name) && !StringUtils.isEmpty(value)) {
	        predicateList.add(builder.equal(attributeNameExpression, name));
	        predicateList.add(builder.equal(attributeValueExpression, value));
	    }
	    
	    Predicate[] predicates = new Predicate[predicateList.size()];
	    predicateList.toArray(predicates);
	    
	    return builder.and(predicates);
	    
	}

}
