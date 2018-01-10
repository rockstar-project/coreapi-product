package com.rockstar.product.web;

import javax.inject.Inject;

import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.LinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.rockstar.product.domain.Option;

@Component
public class OptionResourceAssembler extends ResourceAssemblerSupport<Option, OptionResource> {
	
	@Inject private EntityLinks entityLinks;
	
	public OptionResourceAssembler() {
		super(ProductController.class, OptionResource.class);
	}
	
	public OptionResource toResource(Option option) {
		OptionResource optionResource = null;
		LinkBuilder templateLinkBuilder = this.entityLinks.linkForSingleResource(ProductDetailsResource.class, option.getProductId());
		
		if (option != null) {
			optionResource = new OptionResource();
			optionResource.setName(option.getName());
			optionResource.setValue(option.getValue());
			optionResource.setTitle(option.getTitle());
			optionResource.setImage(option.getImage());
			optionResource.setVersion(option.getVersion());
			optionResource.add(templateLinkBuilder.slash("options").slash(option.getId()).withSelfRel());
		}
		return optionResource;
	}
	
	public Option fromResource(OptionResource optionResource) {
		Option option = null;
		
		if (optionResource != null) {
			option = new Option();
			option.setName(optionResource.getName());
			option.setValue(optionResource.getValue());
			option.setTitle(optionResource.getTitle());
			option.setImage(optionResource.getImage());
			option.setVersion(optionResource.getVersion());
		}
		return option;
	}
}

