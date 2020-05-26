package com.rockstar.product.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.LinkBuilder;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.rockstar.product.domain.Attribute;

@Component
public class AttributeResourceAssembler extends RepresentationModelAssemblerSupport<Attribute, AttributeResource> {
	
	@Autowired private EntityLinks entityLinks;
	
	public AttributeResourceAssembler() {
		super(ProductController.class, AttributeResource.class);
	}
	
	public AttributeResource toModel(Attribute attribute) {
		AttributeResource attributeResource = null;
		LinkBuilder linkBuilder = this.entityLinks.linkForItemResource(ProductResource.class, attribute.getProductId());
		
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
	
	public CollectionModel<AttributeResource> toCollectionModel(Iterable<? extends Attribute> entities) {
        return  super.toCollectionModel(entities);
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

