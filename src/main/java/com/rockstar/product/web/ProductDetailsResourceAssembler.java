package com.rockstar.product.web;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.rockstar.product.domain.Attribute;
import com.rockstar.product.domain.Option;
import com.rockstar.product.domain.Product;

@Component
public class ProductDetailsResourceAssembler extends ResourceAssemblerSupport<Product, ProductDetailsResource> {
	
	@Inject private OptionResourceAssembler optionResourceAssembler;
	@Inject private AttributeResourceAssembler attributeResourceAssembler;
	
	public ProductDetailsResourceAssembler() {
		super(ProductController.class, ProductDetailsResource.class);
	}
	
	public ProductDetailsResource toResource(Product product) {
		ProductDetailsResource productResource = null;
		
		productResource = this.createResourceWithId(product.getId(), product);
		productResource.setName(product.getName());
		productResource.setTitle(product.getTitle());
		productResource.setSubtitle(product.getSubtitle());
		productResource.setDescription(product.getDescription());
		productResource.setImage(product.getImage());
		productResource.setStatus(product.getStatus());
		productResource.setVisibility(product.getVisibility());
		productResource.setFeatured(product.getFeatured());
		productResource.setAuthor(product.getAuthor());
		productResource.setOrganization(product.getOrganization());
		productResource.setVersion(product.getVersion());
		productResource.setCreatedAt(product.getCreatedAt());
		
		List<AttributeResource> attributeResources = null;
		
		if (product.getAttributes() != null && !product.getAttributes().isEmpty()) {
			attributeResources = new ArrayList<AttributeResource>();
			for (Attribute currentAttribute : product.getAttributes()) {
				attributeResources.add(this.attributeResourceAssembler.toResource(currentAttribute));
			}
			productResource.setAttributes(attributeResources);
		}
		
		List<OptionResource> optionResources = null;
		
		if (product.getOptions() != null && !product.getOptions().isEmpty()) {
			optionResources = new ArrayList<OptionResource>();
			for (Option currentOption : product.getOptions()) {
				optionResources.add(this.optionResourceAssembler.toResource(currentOption));
			}
			productResource.setOptions(optionResources);
		}

		
		return productResource;
	}
	
	public Product fromResource(ProductDetailsResource productResource) {
		Product product = null;
		
		product = new Product();
		product.setName(productResource.getName());
		product.setTitle(productResource.getTitle());
		product.setSubtitle(productResource.getSubtitle());
		product.setDescription(productResource.getDescription());
		product.setImage(productResource.getImage());
		product.setStatus(productResource.getStatus());
		product.setVisibility(productResource.getVisibility());
		product.setFeatured(productResource.getFeatured());
		product.setAuthor(productResource.getAuthor());
		product.setOrganization(productResource.getOrganization());
		product.setVersion(productResource.getVersion());
		product.setCreatedAt(productResource.getCreatedAt());
		
		return product;
	}

}
