package com.shopping_service.service.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.shopping_service.persistence.user.User;
import com.shopping_service.persistence.user.UserRepository;

import jakarta.validation.constraints.Email;

@Service
public class UserService implements IUserService{
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public User save(User user) {
		return userRepo.save(user);
	}
	
	@Override
	public User update(User user) {
		return userRepo.save(user);
	}
	
	@Override
	public Optional<User> findByEmail(@Email String email) {
		return userRepo.findByEmail(email);
	}
	
	@Override
	public List<User> findAll() {
		return (List<User>) userRepo.findAll();
	}
	
	@Override
	public Page<User> findAllByFirstName(String firstName, int page, int pageSize){
		return userRepo.findByFirstNameContaining(firstName, PageRequest.of(page, pageSize));
	}

	@Override
	public void delete(User user) {
		userRepo.delete(user);
	}

}
