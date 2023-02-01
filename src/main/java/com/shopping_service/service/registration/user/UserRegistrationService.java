package com.shopping_service.service.registration.user;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping_service.client.exception.ConflictException;
import com.shopping_service.json.request.CreateUser;
import com.shopping_service.persistence.user.User;
import com.shopping_service.security.authority.Role;
import com.shopping_service.security.util.SecurityUtil;
import com.shopping_service.service.user.IUserService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserRegistrationService implements IUserRegistrationService{
	
	@Autowired
	private IUserService userService;
		
	@Override
	public CreateUser register(@Valid CreateUser user) {
		
		if ( !userService.findByEmail(user.getEmail()).isEmpty() )
			throw new ConflictException( "email_exists: ".concat(user.getEmail()) );
		
		User builtUser = User.builder()
				.email(user.getEmail())
				.firstName(user.getEmail())
				.lastName(user.getLastName())				
				.phone(user.getPhoneNumber())
				.locked(false)
				.active(false)
				.role(Role.USER)
				.password(SecurityUtil.passwordEncoder().encode(user.getPassword()))
				.createdAt(new Date())
				.build();
		
		log.info(builtUser.toString());
		
		userService.save(builtUser);
		
		
		return user;
	}

	@Override
	public void confirmToken(String token) {
		
	}

}
