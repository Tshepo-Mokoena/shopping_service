package com.shopping_service.service.registration.user;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopping_service.client.exception.ConflictException;
import com.shopping_service.client.exception.NotFoundException;
import com.shopping_service.json.request.CreateUser;
import com.shopping_service.persistence.cart.Cart;
import com.shopping_service.persistence.token.userconfirmationtoken.UserConfirmationToken;
import com.shopping_service.persistence.user.User;
import com.shopping_service.security.authority.Role;
import com.shopping_service.security.util.SecurityUtil;
import com.shopping_service.service.cart.ICartService;
import com.shopping_service.service.user.IUserService;
import com.shopping_service.service.userconfirmationtoken.IUserConfirmationTokenService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserRegistrationService implements IUserRegistrationService{
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private ICartService cartService;
	
	@Autowired
	private IUserConfirmationTokenService userConfirmationTokenService;
		
	@Override
	@Transactional
	public CreateUser register(@Valid CreateUser user) {
		
		if ( !userService.findByEmail(user.getEmail()).isEmpty() )
			throw new ConflictException( "email_exists: ".concat(user.getEmail()) );
		
		User builtUser = User.builder()
				.firstName(user.getEmail())
				.lastName(user.getLastName())
				.email(user.getEmail())				
				.phone(user.getPhoneNumber())
				.locked(false)
				.active(false)
				.role(Role.USER)
				.password(SecurityUtil.passwordEncoder().encode(user.getPassword()))
				.createdAt(new Date())
				.build();
		
		log.info(builtUser.toString());
		
		userService.save(builtUser);
		
		Cart cart = cartService.create(builtUser);
		
		log.info(cart.toString());
		
		return user;
	}

	@Override
	@Transactional
	public void confirmToken(String token) {
		
		UserConfirmationToken confirmToken = userConfirmationTokenService.findByToken(token)
				.orElseThrow(() -> new NotFoundException("invalid_token: ".concat(token)));
		
		if ( !confirmToken.getExpiresAt().isBefore(LocalDateTime.now()) )
			throw new ConflictException("invalid_token: ".concat(token));
		
		userService.enable(confirmToken.getUser().getEmail(), true);
		
		userConfirmationTokenService.confirmedAt(token, LocalDateTime.now());
		
		//TODO
		
	}

}
