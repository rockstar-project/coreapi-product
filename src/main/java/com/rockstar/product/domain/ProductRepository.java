package com.rockstar.product.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepository extends PagingAndSortingRepository<Product, String>, JpaSpecificationExecutor<Product> {
	
	@Query("select p from Product p where UPPER(p.title) like UPPER(?1) or " +
            "UPPER(p.subtitle) like UPPER(?1)")
    public List<Product> search(String term, Boolean featured, String architecture);
	
	public Product findByName(String name);

}
