package com.rockstar.product.web;

import javax.inject.Inject;

import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.LinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.rockstar.product.domain.Attribute;

@Component
public class AttributeResourceAssembler extends ResourceAssemblerSupport<Attribute, AttributeResource> {
	
	@Inject private EntityLinks entityLinks;
	
	public AttributeResourceAssembler() {
		super(ProductController.class, AttributeResource.class);
	}
	
	public AttributeResource toResource(Attribute attribute) {
		AttributeResource attributeResource = null;
		LinkBuilder linkBuilder = this.entityLinks.linkForSingleResource(ProductResource.class, attribute.getProductId());
		
		if (attribute != null) {
			attributeResource = new AttributeResource();
			attributeResource.setName(attribute.getName());
			attributeResource.setValue(attribute.getValue());
			attributeResource.setTitle(attribute.getTitle());
			attributeResource.setImage(attribute.getImage());
			attributeResource.setVersion(attribute.getVersion());
			attributeResource.add(linkBuilder.slash("attributes").slash(attribute.getId()).withSelfRel());
		}
		return attributeResource;
	}
	
	public Attribute fromResource(AttributeResource attributeResource) {
		Attribute attribute = null;
		
		if (attributeResource != null) {
			attribute = new Attribute();
			attribute.setName(attributeResource.getName());
			attribute.setValue(attributeResource.getValue());
			attribute.setTitle(attributeResource.getTitle());
			attribute.setImage(attributeResource.getImage());
			attribute.setVersion(attributeResource.getVersion());
		}
		return attribute;
	}
}

