package com.rockstar.product.domain;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.rockstar.product.service.NotFoundException;
import com.rockstar.product.service.NotUniqueException;
import com.rockstar.product.service.ProductSearch;
import com.rockstar.product.service.ProductService;

@Service
@Transactional(readOnly=true)
public class ProductServiceImpl implements ProductService {
	
	@Autowired private ProductRepository productRepository;
	@Autowired private AttributeRepository attributeRepository;
	@Autowired private OptionRepository optionRepository;
	@Autowired private MediaRepository mediaRepository;
	
	public Iterable<Product> getAllProducts() {
		return this.productRepository.findAll();
	}
	
	public Page<Product> search(ProductSearch productSearch, Pageable pageRequest) {
		Page<Product> productsPage = null;
		ProductSearchSpecification productSearchSpecification = null;

		if (productSearch != null) {
			productSearchSpecification = new ProductSearchSpecification();
			productSearchSpecification.setQuery(productSearch.getQuery());
			productSearchSpecification.setFeatured(productSearch.getFeatured());
			productSearchSpecification.setVisibility(productSearch.getVisibility());
			productSearchSpecification.setState(productSearch.getState());
			productSearchSpecification.setOrganization(productSearch.getOrganization());
			productSearchSpecification.setPrice(productSearch.getPrice());
			productSearchSpecification.setArchitecture(productSearch.getArchitecture());
			productSearchSpecification.setSpecification(productSearch.getSpecification());
			productSearchSpecification.setLanguage(productSearch.getLanguage());
			productSearchSpecification.setFramework(productSearch.getFramework());
			productsPage = this.productRepository.findAll(productSearchSpecification, pageRequest);
		}
		return productsPage;
	}
	
	public Product getProduct(String productId) {
		return this.retrieveProductById(productId);
	}
	
	@Transactional(readOnly=false)
	public Product createProduct(Product product) {
		Product updatedProduct = null;
		if (product != null) {
			product.setCreatedAt(LocalDateTime.now());
			this.validateUniqueProduct(product);
			updatedProduct = this.productRepository.save(product);
		}
		return updatedProduct;
	}
	
	@Transactional(readOnly=false)
	public void updateProduct(Product product) {
		Product updatedProduct = null;
		
		updatedProduct = this.retrieveProductById(product.getId());
		
		if (StringUtils.hasText(product.getName())) {
			updatedProduct.setName(product.getName());
		}
		if (StringUtils.hasText(product.getTitle())) {
			updatedProduct.setTitle(product.getTitle());
		}
		if (StringUtils.hasText(product.getSubtitle())) {
			updatedProduct.setSubtitle(product.getSubtitle());
		}
		if (StringUtils.hasText(product.getState())) {
			updatedProduct.setState(product.getState());
		}
		if (StringUtils.hasText(product.getPrice())) {
			updatedProduct.setPrice(product.getPrice());
		}
		if (StringUtils.hasText(product.getVisibility())) {
			updatedProduct.setVisibility(product.getVisibility());
		}
		if (StringUtils.hasText(product.getDescription())) {
			updatedProduct.setDescription(product.getDescription());
		}
		if (StringUtils.hasText(product.getImage())) {
			updatedProduct.setImage(product.getImage());
		}
		if (StringUtils.hasText(product.getBlogUrl())) {
			updatedProduct.setBlogUrl(product.getBlogUrl());
		}
		if (StringUtils.hasText(product.getSchemaUrl())) {
			updatedProduct.setSchemaUrl(product.getSchemaUrl());
		}
		if (product.getFeatured() != null) {
			updatedProduct.setFeatured(product.getFeatured());
		}
	}
	
	@Transactional(readOnly=false)
	public void deleteProduct(String productId) {
		this.productRepository.delete(this.retrieveProductById(productId));
	}
	
	private void validateUniqueProduct(Product product) {
		Product persistedProduct = null;
		
		if (!StringUtils.isEmpty(product.getName())) {
			persistedProduct = this.productRepository.findByName(product.getName());
			if (persistedProduct != null) {
				throw new NotUniqueException("product");
			}
		}
	}
	
	private Product retrieveProductById(String productId) {
		Optional<Product> productOption = this.productRepository.findById(productId);
		if (!productOption.isPresent()) {
			throw new NotFoundException("product");
		}
		return productOption.get();
	}
	
	public Iterable<Media> getMediaItems(String productId) {
		return this.mediaRepository.findByProductId(productId);
	}

	public Media getMedia(String productId, String mediaId) {
		Media existingMedia = null;
		
		this.getProduct(productId);
		
		existingMedia = this.retrieveMediaById(mediaId);
		
		if (existingMedia == null) {
			throw new NotFoundException("media");
		}
		
		return existingMedia;
	}
	
	@Transactional(readOnly=false)
	public Media addMedia(Media media) {
		Media  updatedMedia = null;
		if (media != null) {
			updatedMedia = this.mediaRepository.save(media);
		}
		return updatedMedia;
	}
	
	@Transactional(readOnly=false)
	public void removeMedia(String productId, String mediaId) {
		this.getProduct(productId);
		this.mediaRepository.delete(this.retrieveMediaById(mediaId));
	}

	private Media retrieveMediaById(String mediaId) {
		Optional<Media> mediaOption = this.mediaRepository.findById(mediaId);
		if (!mediaOption.isPresent()) {
			throw new NotFoundException("media");
		}
		return mediaOption.get();
	}
	
	public Iterable<Attribute> getAttributes(String productId) {
		return this.attributeRepository.findByProductId(productId);
	}

	public Attribute getAttribute(String productId, String attributeId) {
		Attribute existingAttribute = null;
		
		this.getProduct(productId);
		
		existingAttribute = this.retrieveAttributeById(attributeId);
		
		if (existingAttribute == null) {
			throw new NotFoundException("attribute");
		}
		
		return existingAttribute;
	}
	
	@Transactional(readOnly=false)
	public Attribute addAttribute(Attribute attribute) {
		Attribute  updatedAttribute = null;
		if (attribute != null) {
			this.validateUniqueAttribute(attribute);
			updatedAttribute = this.attributeRepository.save(attribute);
		}
		return updatedAttribute;
	}
	
	@Transactional(readOnly=false)
	public void removeAttribute(String productId, String attributeId) {
		this.getProduct(productId);
		this.attributeRepository.delete(this.retrieveAttributeById(attributeId));
	}

	private void validateUniqueAttribute(Attribute attribute) {
		Attribute persistedAttribute = null;
		
		persistedAttribute = this.attributeRepository.findByProductIdAndName(attribute.getProductId(), attribute.getName());
		if (persistedAttribute != null) {
			throw new NotUniqueException("attribute");
		}
	}
	
	private Attribute retrieveAttributeById(String attributeId) {
		Optional<Attribute> attributeOption = this.attributeRepository.findById(attributeId);
		if (attributeOption.isPresent()) {
			throw new NotFoundException("attribute");
		}
		return attributeOption.get();
	}
	
	public Iterable<Option> getOptions(String productId) {
		return this.optionRepository.findByProductId(productId);
	}

	public Option getOption(String productId, String optionId) {
		Option existingOption = null;
		
		this.getProduct(productId);
		
		existingOption = this.retrieveOptionById(optionId);
		
		if (existingOption == null) {
			throw new NotFoundException("option");
		}
		
		return existingOption;
	}
	
	@Transactional(readOnly=false)
	public Option addOption(Option option) {
		Option  updatedOption = null;
		if (option != null) {
			this.validateUniqueOption(option);
			updatedOption = this.optionRepository.save(option);
		}
		return updatedOption;
	}
	
	@Transactional(readOnly=false)
	public void removeOption(String productId, String optionId) {
		this.getProduct(productId);
		this.optionRepository.delete(this.retrieveOptionById(optionId));
	}

	private void validateUniqueOption(Option option) {
		Option persistedOption = null;
		
		persistedOption = this.optionRepository.findByProductIdAndName(option.getProductId(), option.getName());
		if (persistedOption != null) {
			throw new NotUniqueException("option");
		}
	}
	
	private Option retrieveOptionById(String optionId) {
		Optional<Option> optionOption = this.optionRepository.findById(optionId);
		if (!optionOption.isPresent()) {
			throw new NotFoundException("option");
		}
		return optionOption.get();
	}
}

