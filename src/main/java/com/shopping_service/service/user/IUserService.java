package com.shopping_service.service.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.shopping_service.persistence.user.User;

import jakarta.validation.constraints.Email;

/**
 * 
 * @author Tshepo W Mokoena
 * @version 1.0
 *
 */
public interface IUserService {
	
	/**
	 * @apiNote saves user, returns saved user
	 * @param user
	 * @return user
	 */
	User save(User user);
	
	/**
	 * @apiNote saves user, returns updated user
	 * @param user
	 * @return user
	 */
	User update(User user);
	
	/**
	 * @apiNote search for user by email, returns optional user
	 * @param email
	 * @return Optional<User>
	 */
	Optional<User> findByEmail(@Email String email);	
	
	/**
	 * @apiNote finds All users
	 * @return List of users
	 */
	List<User> findAll();
	
	/**
	 * @apiNote finds users returns page of users
	 * @param firstName
	 * @param page
	 * @param pageSize
	 * @return Page<User>
	 */
	Page<User> findAllByFirstName(String firstName, int page, int pageSize);

	/**
	 * @apiNote deletes user returns void
	 * @param user
	 */
	void delete(User user);

}
