package com.shopping_service.service.registration;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping_service.client.exception.ConflictException;
import com.shopping_service.json.request.CreateUser;
import com.shopping_service.persistence.user.User;
import com.shopping_service.service.IUserService;

@Service
public class RegistrationService implements IRegistrationService{
	
	@Autowired
	private IUserService userService;
		
	@Override
	public CreateUser register(CreateUser user) {
		
		if ( !userService.findByEmail(user.getEmail()).isEmpty() )
			throw new ConflictException( "email exists" + user.getEmail() );
		
		userService.save(User.builder().email(user.getEmail())
				.firstName(user.getEmail()).lastName(user.getLastName())
				.userId(UUID.randomUUID().toString()).build());
		
		
		return user;
	}

}
