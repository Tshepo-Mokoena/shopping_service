package com.shopping_service.persistence.cartitem;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shopping_service.persistence.cart.Cart;

/**
 * 
 * @author Tshepo W Mokoena
 * @version 1.0
 */
@Repository
public interface CartItemRepository extends CrudRepository<CartItem, Long>{
	
	/**
	 * @apiNote finds cart items by cart id
	 * @param cart
	 * @return List<CartItems>
	 */
	List<CartItem> findByCart(Cart cart);

}
