package com.shopping_service.client;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopping_service.client.exception.NotFoundException;
import com.shopping_service.json.response.Response;
import com.shopping_service.persistence.user.User;
import com.shopping_service.service.order.IOrderService;
import com.shopping_service.service.user.IUserService;

@RestController
@RequestMapping("/api/orders")
public class OrderAPI {
	
	private IUserService userService;
	
	@Autowired
	private IOrderService orderService;
	
	@PostMapping
	public Response<?> createOrder(@RequestParam("email") String email){
		return Response.builder()
				.status(HttpStatus.CREATED)
				.message("success")
				.data(orderService.create(findByEmail(email)))
				.build();
	}
	
	
	@GetMapping
	public Response<?> findOrders(@RequestParam("email") String email, 
			@RequestParam("page") Optional<Integer> page, @RequestParam("pageSize") Optional<Integer> pageSize){
		return Response.builder()
				.status(HttpStatus.OK)
				.message("success")
				.data(orderService.findByUser(findByEmail(email), page.orElse(0), pageSize.orElse(10)))
				.build();
	}
	
	
	@GetMapping("/{orderId}")
	public Response<?> findOrders(@PathVariable("orderId") Long orderId){
		return Response.builder()
				.status(HttpStatus.OK)
				.message("success")
				.data(orderService.findById(orderId))
				.build();
	}
	
	@PostMapping("/all")
	public Response<?> findOrders(@RequestParam("page") Optional<Integer> page, 
			@RequestParam("pageSize") Optional<Integer> pageSize){
		return Response.builder()
				.status(HttpStatus.CREATED)
				.message("success")
				.data(orderService.findAll(page.orElse(0), pageSize.orElse(10)))
				.build();
	}
		
	
	private User findByEmail(String email) {
		return userService.findByEmail(email)
				.orElseThrow(() -> new NotFoundException("user_not_found: ".concat(email)));
	}

}
