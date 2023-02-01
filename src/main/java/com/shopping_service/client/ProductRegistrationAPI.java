package com.shopping_service.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping_service.json.request.CreateProduct;
import com.shopping_service.json.response.Response;
import com.shopping_service.service.registration.product.IProductRegistration;

@RestController
@RequestMapping("/api/registration/product")
public class ProductRegistrationAPI {
	
	@Autowired
	private IProductRegistration productRegistration;
	
	@PostMapping
	public Response<?> create(@RequestBody CreateProduct createProduct){
		return Response.builder().status(HttpStatus.CREATED)
				.message("success")
				.data(productRegistration.create(createProduct))
				.build();
		
	}
	
	@PostMapping("/{productId}")
	public Response<?> update(@PathVariable("productId") String productId,@RequestBody CreateProduct createProduct){
		return Response.builder().status(HttpStatus.CREATED)
				.message("success")
				.data(productRegistration.update(productId, createProduct))
				.build();
		
	}
	

}
