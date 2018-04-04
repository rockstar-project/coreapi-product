package com.rockstar.product.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<Media, String> {
	
	List<Media> findByProductId(String productId);

}
