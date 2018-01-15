package com.rockstar.product.web;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.rockstar.product.domain.Product;

@Component
public class ProductInfoResourceAssembler extends ResourceAssemblerSupport<Product, ProductInfoResource> {
	
	public ProductInfoResourceAssembler() {
		super(ProductController.class, ProductInfoResource.class);
	}
	
	public ProductInfoResource toResource(Product template) {
		ProductInfoResource templateResource = null;
		
		templateResource = this.createResourceWithId(template.getId(), template);
		templateResource.setName(template.getName());
		templateResource.setTitle(template.getTitle());
		templateResource.setImage(template.getImage());
		
		return templateResource;
	}
	
}
