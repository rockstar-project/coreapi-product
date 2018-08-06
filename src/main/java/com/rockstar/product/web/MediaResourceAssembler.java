package com.rockstar.product.web;

import javax.inject.Inject;

import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.LinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.rockstar.product.domain.Media;

@Component
public class MediaResourceAssembler extends ResourceAssemblerSupport<Media, MediaResource> {
	
	@Inject private EntityLinks entityLinks;
	
	public MediaResourceAssembler() {
		super(ProductController.class, MediaResource.class);
	}
	
	public MediaResource toResource(Media media) {
		MediaResource mediaResource = null;
		LinkBuilder linkBuilder = this.entityLinks.linkForSingleResource(ProductResource.class, media.getProductId());
		
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


