package com.shopping_service.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping_service.json.request.CreateUser;
import com.shopping_service.json.response.Response;
import com.shopping_service.service.registration.IRegistrationService;

@RestController
@RequestMapping("/api/registration")
public class UserRegistrationAPI {

	@Autowired
	private IRegistrationService registrationService;

	@PostMapping
	public Response<?> register(@RequestBody CreateUser user) {
		return Response.builder().status(HttpStatus.OK).data(registrationService.register(user)).build();
	}

}
