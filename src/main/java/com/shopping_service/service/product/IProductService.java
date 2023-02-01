package com.shopping_service.service.product;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.shopping_service.persistence.product.Product;

public interface IProductService {
	
	void add(Product product);
	
	void save(Product product);
	
	Page<Product> findAllByTitle(String title, int page, int pageSize);
	
	Optional<Product> findByTitle(String title);
	
	Optional<Product> findByProductId(String productId);	

}
