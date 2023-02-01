package com.shopping_service.service.registration.product;

import com.shopping_service.json.request.CreateProduct;

import jakarta.validation.Valid;

/**
 * 
 * @author Tshepo W Mokoena
 * @version 1.0 
 * 
 */
public interface IProductRegistration {
	/**
	 * 
	 * @param createProduct
	 * @return createProduct
	 */
	CreateProduct create(@Valid CreateProduct createProduct);
	
	/**
	 * 
	 * @param productID
	 * @param createProduct
	 * @return createProduct
	 */
	CreateProduct update(String productID, @Valid CreateProduct createProduct);

}
