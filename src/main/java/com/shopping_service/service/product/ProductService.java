package com.shopping_service.service.product;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.shopping_service.persistence.product.Product;
import com.shopping_service.persistence.product.ProductRepository;

@Service
public class ProductService implements IProductService{
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public void add(Product product) {
		productRepository.save(product);
	}

	@Override
	public void save(Product product) {
		product.setUpdatedAt(new Date());
		productRepository.save(product);
	}

	@Override
	public Page<Product> findAllByTitle(String title, int page, int pageSize) {
		return productRepository.findByTitleContaining(title, PageRequest.of(page, pageSize));
	}

	@Override
	public Optional<Product> findByTitle(String title) {
		return productRepository.findByTitle(title);
	}

	@Override
	public Optional<Product> findByProductId(String productId) {		
		return productRepository.findByProductId(productId);
	}

}
