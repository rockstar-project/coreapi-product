package com.rockstar.product.web;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.LinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.rockstar.product.domain.Attribute;
import com.rockstar.product.domain.Media;
import com.rockstar.product.domain.Option;
import com.rockstar.product.domain.Product;

@Component
public class ProductResourceAssembler extends ResourceAssemblerSupport<Product, ProductResource> {
	
	@Inject private EntityLinks entityLinks;
	
	@Inject private OptionResourceAssembler optionResourceAssembler;
	@Inject private AttributeResourceAssembler attributeResourceAssembler;
	@Inject private MediaResourceAssembler mediaResourceAssembler;
	
	public ProductResourceAssembler() {
		super(ProductController.class, ProductResource.class);
	}
	
	public ProductResource toResource(Product product) {
		ProductResource productResource = null;
		LinkBuilder entityLinkBuilder = null;
		
		if (product != null) {
			entityLinkBuilder = this.entityLinks.linkForSingleResource(ProductResource.class, product.getId());
			productResource = this.createResourceWithId(product.getId(), product);
			productResource.setName(product.getName());
			productResource.setTitle(product.getTitle());
			productResource.setSubtitle(product.getSubtitle());
			productResource.setDescription(product.getDescription());
			productResource.setImage(product.getImage());
			productResource.setBlogUrl(product.getBlogUrl());
			productResource.setSchemaUrl(product.getSchemaUrl());
			productResource.setState(product.getState());
			productResource.setPrice(product.getPrice());
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
			
			List<MediaResource> mediaResources = null;
			
			if (product.getMediaItems() != null && !product.getMediaItems().isEmpty()) {
				mediaResources = new ArrayList<MediaResource>();
				for (Media currentMedia : product.getMediaItems()) {
					mediaResources.add(this.mediaResourceAssembler.toResource(currentMedia));
				}
				productResource.setMediaItems(mediaResources);
			}
		}
		
		return productResource;
	}
	
	public Product fromResource(ProductResource productResource) {
		Product product = null;
		
		product = new Product();
		product.setName(productResource.getName());
		product.setTitle(productResource.getTitle());
		product.setSubtitle(productResource.getSubtitle());
		product.setDescription(productResource.getDescription());
		product.setImage(productResource.getImage());
		product.setBlogUrl(productResource.getBlogUrl());
		product.setSchemaUrl(productResource.getSchemaUrl());
		product.setState(productResource.getState());
		product.setPrice(productResource.getPrice());
		product.setVisibility(productResource.getVisibility());
		product.setFeatured(productResource.getFeatured());
		product.setAuthor(productResource.getAuthor());
		product.setOrganization(productResource.getOrganization());
		product.setVersion(productResource.getVersion());
		product.setCreatedAt(productResource.getCreatedAt());
		
		return product;
	}

}
