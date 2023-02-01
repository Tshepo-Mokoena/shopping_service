package com.shopping_service.persistence.cart;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shopping_service.persistence.user.User;

/** 
 * @author Tshepo W Mokoena
 * @version 1.0
 *
 */
@Repository
public interface CartRepository extends CrudRepository<Cart, Long>{
	
	/**
	 * @apiNote finds cart by user id
	 * @param user
	 * @return cart
	 */
	Optional<Cart> findByUser(User user);

}
