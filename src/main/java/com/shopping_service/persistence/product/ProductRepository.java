package com.shopping_service.persistence.product;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long>, CrudRepository<Product, Long> {
	
	Optional<Product> findByProductId(String title);
	
	Page<Product> findByTitleContaining(String title, Pageable pageable);

	Optional<Product> findByTitle(String title);

}
