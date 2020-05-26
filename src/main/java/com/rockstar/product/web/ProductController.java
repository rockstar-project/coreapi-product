package com.rockstar.product.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rockstar.product.domain.Attribute;
import com.rockstar.product.domain.Media;
import com.rockstar.product.domain.Option;
import com.rockstar.product.domain.Product;
import com.rockstar.product.service.ProductSearch;
import com.rockstar.product.service.ProductService;

@RestController
@RequestMapping("/products")
@ExposesResourceFor(ProductResource.class)
public class ProductController {
	
	@Autowired private ProductService productService;
	@Autowired private ProductResourceAssembler productResourceAssembler;
	@Autowired private OptionResourceAssembler optionResourceAssembler;
	@Autowired private AttributeResourceAssembler attributeResourceAssembler;
	@Autowired private MediaResourceAssembler mediaResourceAssembler;
	
	@RequestMapping(method=RequestMethod.POST)
	public HttpEntity<Void> createProduct(@RequestBody @Valid ProductResource productResource) {
		HttpHeaders headers = null;
		Product newProduct = null;
		Product updatedProduct = null;
		
		newProduct = this.productResourceAssembler.fromResource(productResource);
		updatedProduct = this.productService.createProduct(newProduct);
		headers = new HttpHeaders();
		headers.setLocation(WebMvcLinkBuilder.linkTo(ProductController.class).slash(updatedProduct.getId()).toUri());
		
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<CollectionModel<ProductResource>> getProducts() { 
       CollectionModel<ProductResource> productResources = this.productResourceAssembler.toCollectionModel(this.productService.getAllProducts());
       
       return new ResponseEntity<CollectionModel<ProductResource>>(productResources, HttpStatus.OK);
    }
	
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public HttpEntity<PagedModel<ProductResource>> search(ProductSearch productSearch,
			@PageableDefault(page = 0, size = 10, sort="title") Pageable pageable, 
			PagedResourcesAssembler<Product> pageResourcesAssembler) {
		
		Page<Product> productsPage = null;
		PagedModel<ProductResource> productResourcePage = null;
		productsPage = this.productService.search(productSearch, pageable);
		
		productResourcePage = pageResourcesAssembler.toModel(productsPage, this.productResourceAssembler);
		return new ResponseEntity<PagedModel<ProductResource>>(productResourcePage, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public HttpEntity<ProductResource> getProduct(@PathVariable("id") String productId) {
		Product product = this.productService.getProduct(productId);
		ProductResource productResource = this.productResourceAssembler.toModel(product);
		
		return new ResponseEntity<ProductResource>(productResource, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PATCH)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateProduct(@PathVariable("id") String productId, @RequestBody Product product) {
		product.setId(productId);
		this.productService.updateProduct(product);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteProduct(@PathVariable("id") String productId) {
		this.productService.deleteProduct(productId);
	}
	
	@RequestMapping(value="/{id}/mediaitems", method=RequestMethod.GET)
	public HttpEntity<CollectionModel<MediaResource>> getProductMedias(@PathVariable("id") String productId) {
		CollectionModel<MediaResource> mediaResourceList = this.mediaResourceAssembler.toCollectionModel(this.productService.getMediaItems(productId));
		return new ResponseEntity<CollectionModel<MediaResource>>(mediaResourceList, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}/mediaitems", method=RequestMethod.POST)
	public HttpEntity<Void> createProductMedia(@PathVariable("id") String productId, @RequestBody MediaResource mediaResource) {
		HttpHeaders headers = null;
		Media updatedMedia = null;
		Media media = this.mediaResourceAssembler.fromResource(mediaResource);
		media.setProductId(productId);
		updatedMedia = this.productService.addMedia(media);
		headers = new HttpHeaders();
		headers.setLocation(WebMvcLinkBuilder.linkTo(ProductController.class).slash(productId).slash("mediaitems").slash(updatedMedia.getId().toString()).toUri());
		
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/{id}/mediaitems/{media_id}", method=RequestMethod.GET)
	public HttpEntity<MediaResource> getProductMedia(@PathVariable("id") String productId, @PathVariable("media_id") String mediaId) {
		MediaResource mediaResource = null;
		mediaResource = this.mediaResourceAssembler.toModel(this.productService.getMedia(productId, mediaId));
		return new ResponseEntity<MediaResource>(mediaResource, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}/mediaitems/{media_id}", method=RequestMethod.DELETE)
	public void deleteProductMedia(@PathVariable("id") String productId, @PathVariable("media_id") String mediaId) {
		Media media = new Media();
		media.setId(mediaId);
		media.setProductId(productId);
		this.productService.removeMedia(productId, mediaId);
	}
	
	@RequestMapping(value="/{id}/attributes", method=RequestMethod.GET)
	public HttpEntity<CollectionModel<AttributeResource>> getProductAttributes(@PathVariable("id") String productId) {
		CollectionModel<AttributeResource> attributeResourceList = this.attributeResourceAssembler.toCollectionModel(this.productService.getAttributes(productId));
		return new ResponseEntity<CollectionModel<AttributeResource>>(attributeResourceList, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}/attributes", method=RequestMethod.POST)
	public HttpEntity<Void> createProductAttribute(@PathVariable("id") String productId, @RequestBody AttributeResource attributeResource) {
		HttpHeaders headers = null;
		Attribute updatedAttribute = null;
		Attribute attribute = this.attributeResourceAssembler.fromResource(attributeResource);
		attribute.setProductId(productId);
		updatedAttribute = this.productService.addAttribute(attribute);
		headers = new HttpHeaders();
		headers.setLocation(WebMvcLinkBuilder.linkTo(ProductController.class).slash(productId).slash("attributes").slash(updatedAttribute.getId().toString()).toUri());
		
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/{id}/attributes/{attribute_id}", method=RequestMethod.GET)
	public HttpEntity<AttributeResource> getProductAttribute(@PathVariable("id") String productId, @PathVariable("attribute_id") String attributeId) {
		AttributeResource attributeResource = null;
		attributeResource = this.attributeResourceAssembler.toModel(this.productService.getAttribute(productId, attributeId));
		return new ResponseEntity<AttributeResource>(attributeResource, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}/attributes/{attribute_id}", method=RequestMethod.DELETE)
	public void deleteProductAttribute(@PathVariable("id") String productId, @PathVariable("attribute_id") String attributeId) {
		Attribute attribute = new Attribute();
		attribute.setId(attributeId);
		attribute.setProductId(productId);
		this.productService.removeAttribute(productId, attributeId);
	}
	
	@RequestMapping(value="/{id}/options", method=RequestMethod.GET)
	public HttpEntity<CollectionModel<OptionResource>> getProductOptions(@PathVariable("id") String productId) {
		CollectionModel<OptionResource> optionResourceList = this.optionResourceAssembler.toCollectionModel(this.productService.getOptions(productId));
		return new ResponseEntity<CollectionModel<OptionResource>>(optionResourceList, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}/options", method=RequestMethod.POST)
	public HttpEntity<Void> createProductOption(@PathVariable("id") String productId, @RequestBody OptionResource optionResource) {
		HttpHeaders headers = null;
		Option updatedOption = null;
		Option option = this.optionResourceAssembler.fromResource(optionResource);
		option.setProductId(productId);
		updatedOption = this.productService.addOption(option);
		headers = new HttpHeaders();
		headers.setLocation(WebMvcLinkBuilder.linkTo(ProductController.class).slash(productId).slash("options").slash(updatedOption.getId().toString()).toUri());
		
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/{id}/options/{option_id}", method=RequestMethod.GET)
	public HttpEntity<OptionResource> getProductOption(@PathVariable("id") String productId, @PathVariable("option_id") String optionId) {
		OptionResource optionResource = null;
		optionResource = this.optionResourceAssembler.toModel(this.productService.getOption(productId, optionId));
		return new ResponseEntity<OptionResource>(optionResource, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}/options/{option_id}", method=RequestMethod.DELETE)
	public void deleteProductOption(@PathVariable("id") String productId, @PathVariable("option_id") String optionId) {
		Option option = new Option();
		option.setId(optionId);
		option.setProductId(productId);
		this.productService.removeOption(productId, optionId);
	}
}