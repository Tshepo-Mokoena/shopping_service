package com.shopping_service.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopping_service.json.request.AuthRequest;
import com.shopping_service.json.request.PasswordRequest;
import com.shopping_service.json.response.AuthResponse;
import com.shopping_service.json.response.Response;
import com.shopping_service.service.authentication.IAuthenticationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth/login")
public class AuthAPI {

	@Autowired
	private IAuthenticationService authService;

	@PostMapping
	public Response<?> login(@Valid @RequestBody AuthRequest auth) {
		return Response.builder().status(HttpStatus.OK).message("success")
				.data(AuthResponse.builder().user(authService.signIn(auth)).build()).build();
	}
	
	@GetMapping
	public Response<?> forgotPassword(@RequestParam("email") String email) {
		return Response.builder().status(HttpStatus.OK).message("success")
				.data(authService.forgotPassword(email)).build();
	}
	
	@PutMapping
	public Response<?> changePassword(@RequestParam("token") String token,
			@RequestBody PasswordRequest passwordRequest) {
		return Response.builder().status(HttpStatus.OK).message("success")
				.data(authService.changePassword(token, passwordRequest)).build();
	}

}
