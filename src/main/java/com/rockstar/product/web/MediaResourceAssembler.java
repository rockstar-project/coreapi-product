package com.rockstar.product.web;

import java.util.Arrays;

import javax.inject.Inject;

import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.LinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

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
			mediaResource.setTitle(media.getTitle());
			mediaResource.setSrc(media.getSrc());
			mediaResource.setThumbnail(media.getThumbnail());
			mediaResource.setType(media.getType());
			if (!StringUtils.isEmpty(media.getTags())) {
				String[] tagsArray = StringUtils.commaDelimitedListToStringArray(media.getTags());
				mediaResource.setTags(Arrays.asList(tagsArray));
			}
			mediaResource.add(linkBuilder.slash("mediaitems").slash(media.getId()).withSelfRel());
		}
		return mediaResource;
	}
	
	public Media fromResource(MediaResource mediaResource) {
		Media media = null;
		
		if (mediaResource != null) {
			media = new Media();
			media.setTitle(mediaResource.getTitle());
			media.setSrc(mediaResource.getSrc());
			media.setType(mediaResource.getType());
			media.setThumbnail(mediaResource.getThumbnail());
			if (mediaResource.getTags() != null && !mediaResource.getTags().isEmpty()) {
				media.setTags(StringUtils.arrayToCommaDelimitedString(mediaResource.getTags().toArray()));
			}
		}
		return media;
	}
}


