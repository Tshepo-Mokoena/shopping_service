package com.shopping_service.client;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopping_service.client.exception.NotFoundException;
import com.shopping_service.json.response.AuthResponse;
import com.shopping_service.json.response.Response;
import com.shopping_service.persistence.user.User;
import com.shopping_service.security.authority.Role;
import com.shopping_service.service.user.IUserService;

@RestController
@RequestMapping("/api/system")
public class SystemAdminAPI {
	
	@Autowired
	private IUserService userService;
	
	@PutMapping("/administrator")
	public Response<?> createAdminstrator(@RequestParam("email") String email){
		
		User user = userService.findByEmail(email)
				.orElseThrow(() -> new NotFoundException("user_not_found: ".concat(email)));
		
		user.setRole(Role.ADMINISTRATOR);
		
		user.setUpdatedAt(LocalDateTime.now());
		
		userService.save(user);
		
		return Response.builder()
				.desc("success")
				.status(HttpStatus.OK)
				.data(
						AuthResponse.builder().user(user).build())
				.build();
	}
	
	@PutMapping("/super-administrator")
	public Response<?> createSuperAdminstrator(@RequestParam("email") String email){
		
		User user = userService.findByEmail(email)
				.orElseThrow(() -> new NotFoundException("user_not_found: ".concat(email)));
		
		user.setRole(Role.SUPER_ADMINISTRATOR);
		
		user.setUpdatedAt(LocalDateTime.now());
		
		userService.save(user);
		
		return Response.builder()
				.desc("success")
				.status(HttpStatus.OK)
				.data(
						AuthResponse.builder().user(user).build())
				.build();
	}

}
