package com.shopping_service.service.registration.user;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping_service.client.exception.NotFoundException;
import com.shopping_service.json.request.CreateUser;
import com.shopping_service.persistence.token.userconfirmationtoken.UserConfirmationToken;
import com.shopping_service.persistence.user.User;
import com.shopping_service.service.token.userconfirmationtoken.IUserConfirmationTokenService;
import com.shopping_service.service.user.IUserService;
import com.shopping_service.util.Util;

import jakarta.validation.Valid;

@Service
public class UserRegistrationService implements IUserRegistrationService {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IUserConfirmationTokenService userConfirmTokeService;

	@Override
	public CreateUser register(@Valid CreateUser user) {
		
		User newUser = User.builder()
				.firstName(user.getFirstName())
				.lastName(user.getLastName())
				.email(user.getEmail())
				.active(false)
				.locked(false)
				.createdAt(new Date())
				.password(user.getPassword())
				.phone(user.getPhoneNumber())
				.build();
		
		User savedUser = userService.save(newUser);
				
		String token = generateUserConfirmToken();		
		
		UserConfirmationToken userConfirmationToken = UserConfirmationToken.builder()
				.token(token)
				.user(savedUser)
				.expiresAt(LocalDateTime.now().plusMonths(1))
				.createdAt(LocalDateTime.now())
				.build();
		
		userConfirmTokeService.save(userConfirmationToken);
		
		return null;
	}

	@Override
	public void confirmToken(String token) {
		
		UserConfirmationToken userConfirmationToken = userConfirmTokeService.findByToken(token)
				.orElseThrow(() -> new NotFoundException("invalid_token:".concat(token)));
		
		if (userConfirmationToken.getExpiresAt().isAfter(LocalDateTime.now())){
			
			if (userConfirmationToken.getUser().getLastLogin() == null) {
								
				String newToken = generateUserConfirmToken();

				UserConfirmationToken newUserConfirmationToken = UserConfirmationToken.builder()
						.token(newToken)
						.user(userConfirmationToken.getUser())
						.expiresAt(LocalDateTime.now().plusMonths(1))
						.createdAt(LocalDateTime.now())
						.build();
				
				userConfirmTokeService.save(newUserConfirmationToken);
				
			}
			
		}		
		
	}
	
	private String generateUserConfirmToken() {
		
		String token = null;		
		
		boolean isUnique = false;		
		
		while (isUnique == false) {			
			
			token = Util.generateUniqueNumericUUId();			
			
			if (userConfirmTokeService.findByToken(token).isEmpty())
				isUnique = true;
			
		}	
		
		return token;
	}
	
}