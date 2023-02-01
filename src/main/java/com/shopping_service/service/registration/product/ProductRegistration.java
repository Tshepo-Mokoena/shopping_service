package com.shopping_service.service.registration.product;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping_service.client.exception.ConflictException;
import com.shopping_service.client.exception.NotFoundException;
import com.shopping_service.json.request.CreateProduct;
import com.shopping_service.persistence.product.Product;
import com.shopping_service.service.product.IProductService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductRegistration implements IProductRegistration {

	@Autowired
	private IProductService productService;

	@Override
	public CreateProduct create(@Valid CreateProduct createProduct) {
		
		if (!productService.findByTitle(createProduct.getTitle()).isEmpty())
			throw new ConflictException("product title exists: ".concat(createProduct.getTitle()));

		productService.add(Product.builder().title(createProduct.getTitle()).subTitle(createProduct.getSubTitle())
				.desc(createProduct.getDesc()).price(createProduct.getPrice()).qty(createProduct.getQty()).createdAt(new Date()).build());

		return createProduct;
	}

	@Override
	public CreateProduct update(String productID, @Valid CreateProduct createProduct) {

		Product product = productService.findByProductId(productID)
				.orElseThrow(() -> new NotFoundException("product not found: ".concat(productID)));

		Optional<Product> getByTitle = productService.findByTitle(createProduct.getTitle());

		if (!getByTitle.isEmpty())
			if (product.getId() != getByTitle.get().getId())
				throw new ConflictException("product title exists: ".concat(product.getTitle()));

		product.setTitle(createProduct.getTitle());
		product.setSubTitle(createProduct.getSubTitle());
		product.setDesc(createProduct.getDesc());
		product.setQty(createProduct.getQty());
		product.setPrice(createProduct.getPrice());
		product.setUpdatedAt(new Date());

		productService.save(product);

		return createProduct;
	}

}
