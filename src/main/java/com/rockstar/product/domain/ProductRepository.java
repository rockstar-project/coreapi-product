package com.rockstar.product.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepository extends PagingAndSortingRepository<Product, String>, JpaSpecificationExecutor<Product> {
	
	@Query("select t from Template t where UPPER(t.title) like UPPER(?1) or " +
            "UPPER(t.subtitle) like UPPER(?1)")
    public List<Product> search(String term, Boolean featured, String architecture);
	
	public Product findByName(String name);

}
