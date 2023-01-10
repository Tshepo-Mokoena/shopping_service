package com.shopping_service.persistence.user;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long>, CrudRepository<User, Long>{
	
	Optional<User> findByEmail(String email);
	
	Page<User> findByFirstNameContaining(String firstName, Pageable pageable);

}
