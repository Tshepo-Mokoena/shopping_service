package com.shopping_service.client;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopping_service.client.exception.ConflictException;
import com.shopping_service.client.exception.NotFoundException;
import com.shopping_service.json.request.CreateUser;
import com.shopping_service.json.response.Response;
import com.shopping_service.persistence.user.User;
import com.shopping_service.service.IUserService;

@RestController
@RequestMapping("/api/users")
public class UserAPI {

	@Autowired
	private IUserService userService;
	
	@GetMapping
	public Response<?> getUsers(@RequestParam Optional<String> keyword, @RequestParam Optional<Integer> page,
			@RequestParam Optional<Integer> pageSize) {
		return buildResponse(userService.findAllByFirstName(keyword.orElse(""), page.orElse(0), pageSize.orElse(0)));
	}

	@GetMapping("/{email}")
	public Response<?> getUser(@PathVariable("email") String email){
				
		return buildResponse(userService.findByEmail(email)
				.orElseThrow(() -> new NotFoundException("email not found: " + email)));
	}

	@PostMapping
	public Response<?> createUser(@RequestBody CreateUser user) {
		
		if (!userService.findByEmail(user.getEmail()).isEmpty())
			throw new ConflictException("email exists: " + user.getEmail());
		
		return buildResponse(userService.save(User.builder().firstName(user.getFirstName()).lastName(user.getLastName())
				.email(user.getEmail()).build()));
	}

	@PutMapping
	public Response<?> updateUser(@RequestBody CreateUser user) {

		if (userService.findByEmail(user.getEmail()).isEmpty())
			throw new NotFoundException("email not found: " + user.getEmail());

		return buildResponse(userService.save(User.builder().firstName(user.getFirstName()).lastName(user.getLastName())
				.email(user.getEmail()).build()));
	}
	
	@DeleteMapping("/{email}")
	public Response<?> deleteUser(@PathVariable("email") String email) {
		
		userService.deleteUser(userService.findByEmail(email)
				.orElseThrow(() -> new NotFoundException("email not found: " + email)));
		
		return Response.builder().status(HttpStatus.OK).build();
	}
	
	private Response<?> buildResponse(Object data) {
		return Response.builder().status(HttpStatus.OK).message("success").data(data).build();
	}

}
