package com.shopping_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.shopping_service.persistence.user.User;

import jakarta.validation.constraints.Email;

public interface IUserService {
	
	User save(User user);
	
	User update(User user);
	
	Optional<User> findByEmail(@Email String email);	
	
	List<User> findAll();
	
	Page<User> findAllByFirstName(String firstName, int page, int pageSize);

	void deleteUser(User user);

}
