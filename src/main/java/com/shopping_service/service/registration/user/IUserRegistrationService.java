package com.shopping_service.service.registration.user;

import com.shopping_service.json.request.CreateUser;

import jakarta.validation.Valid;

/**
 * 
 * @author Tshepo W Mokoena
 * @version 1.0 
 *
 */
public interface IUserRegistrationService {
	
	/**
	 * @apiNote registers new user
	 * @param CreateUser
	 * @return CreatedUser
	 */
	CreateUser register(@Valid CreateUser user);
	
	/**
	 * @apiNote confirms if email is valid and activates user if token valid
	 * @param token
	 * @return void
	 */
	void confirmToken(String token);

}
