package com.shopping_service.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopping_service.client.exception.NotFoundException;
import com.shopping_service.json.request.CartRequest;
import com.shopping_service.json.response.Response;
import com.shopping_service.persistence.user.User;
import com.shopping_service.service.cart.ICartService;
import com.shopping_service.service.user.IUserService;

@RestController
@RequestMapping("/api/cart")
public class CartAPI {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private ICartService cartService;
	
	@PostMapping
	public Response<?> addToCart(@RequestParam("email") String email, CartRequest cartRequest){
		return Response.builder()
				.status(HttpStatus.OK)
				.message("success")
				.data(cartService.addToCart(findUserByEmail(email), cartRequest))
				.build();
	}
	
	@GetMapping
	public Response<?> cart(@RequestParam("email") String email, CartRequest cartRequest){
		return Response.builder()
				.status(HttpStatus.OK)
				.message("success")
				.data(cartService.findByUser(findUserByEmail(email)))
				.build();
	}
	
	@DeleteMapping
	public Response<?> removeFromCart(@RequestParam("email") String email, CartRequest cartRequest){
		return Response.builder()
				.status(HttpStatus.OK)
				.message("success")
				.data(cartService.clear(findUserByEmail(email)))
				.build();
	}
	
	private User findUserByEmail(String email) {
		return userService.findByEmail(email)
				.orElseThrow( () -> new NotFoundException("email_not_found: ".concat(email) ));
	}

}
