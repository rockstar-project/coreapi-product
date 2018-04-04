package com.rockstar.product.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.rockstar.product.domain.Attribute;
import com.rockstar.product.domain.Media;
import com.rockstar.product.domain.Option;
import com.rockstar.product.domain.Product;

public interface ProductService {
	
	public List<Product> getAllProducts();
	public Product getProduct(String identifier);
	public Product createProduct(Product product);
	public void updateProduct(Product product);
	public void deleteProduct(String identifier);
	public Page<Product> search(ProductSearch productSearch, Pageable pageRequest);
	
	public List<Media> getMediaItems(String productId);
	public Media getMedia(String productId, String mediaId);
	public Media addMedia(Media media);
	public void removeMedia(String productId, String mediaId);
	
	public List<Option> getOptions(String productId);
	public Option getOption(String productId, String optionId);
	public Option addOption(Option option);
	public void removeOption(String productId, String optionId);
	
	public List<Attribute> getAttributes(String productId);
	public Attribute getAttribute(String productId, String attributeId);
	public Attribute addAttribute(Attribute attribute);
	public void removeAttribute(String productId, String attributeId);
	
}
