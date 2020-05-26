package com.rockstar.product.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.LinkBuilder;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.rockstar.product.domain.Media;

@Component
public class MediaResourceAssembler extends RepresentationModelAssemblerSupport<Media, MediaResource> {
	
	@Autowired private EntityLinks entityLinks;
	
	public MediaResourceAssembler() {
		super(ProductController.class, MediaResource.class);
	}
	
	public MediaResource toModel(Media media) {
		MediaResource mediaResource = null;
		LinkBuilder linkBuilder = this.entityLinks.linkForItemResource(ProductResource.class, media.getProductId());
		
		if (media != null) {
			mediaResource = new MediaResource();
			mediaResource.setSlug(media.getSlug());
			mediaResource.setTitle(media.getTitle());
			mediaResource.setSrc(media.getSrc());
			mediaResource.setThumbnail(media.getThumbnail());
			mediaResource.setType(media.getType());
			mediaResource.setOrder(media.getOrder());
			mediaResource.add(linkBuilder.slash("mediaitems").slash(media.getId()).withSelfRel());
		}
		return mediaResource;
	}
	
	public Media fromResource(MediaResource mediaResource) {
		Media media = null;
		
		if (mediaResource != null) {
			media = new Media();
			media.setSlug(mediaResource.getSlug());
			media.setTitle(mediaResource.getTitle());
			media.setSrc(mediaResource.getSrc());
			media.setType(mediaResource.getType());
			media.setOrder(mediaResource.getOrder());
			media.setThumbnail(mediaResource.getThumbnail());
		}
		return media;
	}
}


