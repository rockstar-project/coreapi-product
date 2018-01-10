package com.rockstar.product.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<Option, String> {
	
	List<Option> findByProductId(String productId);
	public Option findByProductIdAndName(String productId, String name);

}
