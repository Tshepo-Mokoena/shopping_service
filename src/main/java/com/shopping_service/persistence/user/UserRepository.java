package com.shopping_service.persistence.user;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long>, CrudRepository<User, Long>{
	
	Optional<User> findByEmail(String email);
	
	Page<User> findByFirstNameContaining(String firstName, Pageable pageable);
	
	@Modifying
	@Query("update User set active = :active where email = :email")
	void enable(@Param("email") String email, @Param("active") boolean active);

}
