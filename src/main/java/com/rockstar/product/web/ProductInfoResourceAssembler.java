package com.rockstar.product.web;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.rockstar.product.domain.Attribute;
import com.rockstar.product.domain.Product;

@Component
public class ProductInfoResourceAssembler extends ResourceAssemblerSupport<Product, ProductInfoResource> {
	
	@Inject private AttributeResourceAssembler attributeResourceAssembler;
	
	public ProductInfoResourceAssembler() {
		super(ProductController.class, ProductInfoResource.class);
	}
	
	public ProductInfoResource toResource(Product product) {
		ProductInfoResource productResource = null;
		
		if (product != null) {
			productResource = this.createResourceWithId(product.getId(), product);
			productResource.setName(product.getName());
			productResource.setTitle(product.getTitle());
			productResource.setSubtitle(product.getSubtitle());
			productResource.setImage(product.getImage());
			
			List<AttributeResource> attributeResources = null;
			
			if (product.getAttributes() != null && !product.getAttributes().isEmpty()) {
				attributeResources = new ArrayList<AttributeResource>();
				for (Attribute currentAttribute : product.getAttributes()) {
					attributeResources.add(this.attributeResourceAssembler.toResource(currentAttribute));
				}
				productResource.setAttributes(attributeResources);
			}
			// productResource.add(linkTo(methodOn(ProductController.class).getProductAttributes(product.getId())).withRel("attributes"));
			productResource.add(linkTo(methodOn(ProductController.class).getProductOptions(product.getId())).withRel("options"));
		}
		return productResource;
	}
	
}
