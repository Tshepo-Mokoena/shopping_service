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
	private IUserConfirmationTokenService userConfirmService;

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
		
		boolean isUnique = false;
		
		String token = null;
		
		while (!isUnique) {
			
			token = Util.generateUniqueNumericUUId();
			
			if (!userConfirmService.findByToken(token).isPresent())
				isUnique = true;
			
		}		
		
		
		UserConfirmationToken userConfirmationToken = UserConfirmationToken.builder()
				.token(token)
				.user(savedUser)
				.expiresAt(LocalDateTime.now().plusMonths(1))
				.createdAt(LocalDateTime.now())
				.build();
		
		userConfirmService.save(userConfirmationToken);
		
		return null;
	}

	@Override
	public void confirmToken(String token) {
		
		UserConfirmationToken userConfirmationToken = userConfirmService.findByToken(token)
				.orElseThrow(() -> new NotFoundException("invalid_token:".concat(token)));
		
		if (userConfirmationToken.getExpiresAt().isAfter(LocalDateTime.now())){
			
			if (userConfirmationToken.getUser().getLastLogin() == null) {
				
				boolean isUnique = false;
				
				String newToken = null;
				
				while (!isUnique) {
					
					newToken = Util.generateUniqueNumericUUId();
					
					if (!userConfirmService.findByToken(newToken).isPresent())
						isUnique = true;
					
				}

				UserConfirmationToken newUserConfirmationToken = UserConfirmationToken.builder()
						.token(newToken)
						.user(userConfirmationToken.getUser())
						.expiresAt(LocalDateTime.now().plusMonths(1))
						.createdAt(LocalDateTime.now())
						.build();
				
				userConfirmService.save(newUserConfirmationToken);
				
			}
			
		}
		
	}
	
}