package com.rockstar.product.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AttributeRepository extends JpaRepository<Attribute, String> {
	
	List<Attribute> findByProductId(String productId);
	public Attribute findByProductIdAndName(String productId, String name);

}
