package com.shopping_service.service.registration;

import com.shopping_service.json.request.CreateUser;

import jakarta.validation.Valid;

/**
 * 
 * @author Tshepo W Mokoena
 * @version 1.0 
 *
 */
public interface IRegistrationService {
	
	/**
	 * returns user created
	 * @param CreateUser
	 * @return CreatedUser
	 */
	CreateUser register(@Valid CreateUser user);

}
