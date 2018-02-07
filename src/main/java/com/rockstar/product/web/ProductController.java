package com.rockstar.product.web;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.PagedResources;
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
import com.rockstar.product.domain.Option;
import com.rockstar.product.domain.Product;
import com.rockstar.product.service.ProductSearch;
import com.rockstar.product.service.ProductService;

@RestController
@RequestMapping("/products")
@ExposesResourceFor(ProductDetailsResource.class)
public class ProductController {
	
	@Inject private ProductService productService;
	@Inject private ProductDetailsResourceAssembler productDetailsResourceAssembler;
	@Inject private ProductInfoResourceAssembler productInfoResourceAssembler;
	@Inject private OptionResourceAssembler optionResourceAssembler;
	@Inject private AttributeResourceAssembler attributeResourceAssembler;
	
	@RequestMapping(method=RequestMethod.POST)
	public HttpEntity<Void> createProduct(@RequestBody @Valid ProductDetailsResource productResource) {
		HttpHeaders headers = null;
		Product newProduct = null;
		Product updatedProduct = null;
		
		newProduct = this.productDetailsResourceAssembler.fromResource(productResource);
		updatedProduct = this.productService.createProduct(newProduct);
		headers = new HttpHeaders();
		headers.setLocation(linkTo(ProductController.class).slash(updatedProduct.getId()).toUri());
		
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ProductInfoResource>> getProducts() { 
       List<ProductInfoResource> productResources = null;
       List<Product> products = this.productService.getAllProducts();
       productResources = this.productInfoResourceAssembler.toResources(products);
       
       return new ResponseEntity<List<ProductInfoResource>>(productResources, HttpStatus.OK);
    }
	
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public HttpEntity<PagedResources<ProductInfoResource>> search(ProductSearch productSearch,
			@PageableDefault(page = 0, size = 10, sort="title") Pageable pageable, 
			PagedResourcesAssembler<Product> pageResourcesAssembler) {
		
		Page<Product> productsPage = null;
		PagedResources<ProductInfoResource> productInfoResourcePage = null;
		productsPage = this.productService.search(productSearch, pageable);
		
		productInfoResourcePage = pageResourcesAssembler.toResource(productsPage, this.productInfoResourceAssembler);
		return new ResponseEntity<PagedResources<ProductInfoResource>>(productInfoResourcePage, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public HttpEntity<ProductDetailsResource> getProduct(@PathVariable("id") String productId) {
		Product product = this.productService.getProduct(productId);
		ProductDetailsResource productResource = this.productDetailsResourceAssembler.toResource(product);
		
		return new ResponseEntity<ProductDetailsResource>(productResource, HttpStatus.OK);
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
	
	@RequestMapping(value="/{id}/attributes", method=RequestMethod.GET)
	public HttpEntity<List<AttributeResource>> getProductAttributes(@PathVariable("id") String productId) {
		List<Attribute> productAttributes = this.productService.getAttributes(productId);
		List<AttributeResource> attributeResourceList = this.attributeResourceAssembler.toResources(productAttributes);
		return new ResponseEntity<List<AttributeResource>>(attributeResourceList, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}/attributes", method=RequestMethod.POST)
	public HttpEntity<Void> createProductAttribute(@PathVariable("id") String productId, @RequestBody AttributeResource attributeResource) {
		HttpHeaders headers = null;
		Attribute updatedAttribute = null;
		Attribute attribute = this.attributeResourceAssembler.fromResource(attributeResource);
		attribute.setProductId(productId);
		updatedAttribute = this.productService.addAttribute(attribute);
		headers = new HttpHeaders();
		headers.setLocation(linkTo(ProductController.class).slash(productId).slash("attributes").slash(updatedAttribute.getId().toString()).toUri());
		
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/{id}/attributes/{attribute_id}", method=RequestMethod.GET)
	public HttpEntity<AttributeResource> getProductAttribute(@PathVariable("id") String productId, @PathVariable("attribute_id") String attributeId) {
		AttributeResource attributeResource = null;
		attributeResource = this.attributeResourceAssembler.toResource(this.productService.getAttribute(productId, attributeId));
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
	public HttpEntity<List<OptionResource>> getProductOptions(@PathVariable("id") String productId) {
		List<Option> productOptions = this.productService.getOptions(productId);
		List<OptionResource> optionResourceList = this.optionResourceAssembler.toResources(productOptions);
		return new ResponseEntity<List<OptionResource>>(optionResourceList, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}/options", method=RequestMethod.POST)
	public HttpEntity<Void> createProductOption(@PathVariable("id") String productId, @RequestBody OptionResource optionResource) {
		HttpHeaders headers = null;
		Option updatedOption = null;
		Option option = this.optionResourceAssembler.fromResource(optionResource);
		option.setProductId(productId);
		updatedOption = this.productService.addOption(option);
		headers = new HttpHeaders();
		headers.setLocation(linkTo(ProductController.class).slash(productId).slash("options").slash(updatedOption.getId().toString()).toUri());
		
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/{id}/options/{option_id}", method=RequestMethod.GET)
	public HttpEntity<OptionResource> getProductOption(@PathVariable("id") String productId, @PathVariable("option_id") String optionId) {
		OptionResource optionResource = null;
		optionResource = this.optionResourceAssembler.toResource(this.productService.getOption(productId, optionId));
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