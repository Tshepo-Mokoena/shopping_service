package com.shopping_service.service.cart;

import com.shopping_service.json.request.CartRequest;
import com.shopping_service.persistence.cart.Cart;
import com.shopping_service.persistence.user.User;

/**
 * 
 * @author Tshepo W Mokoena
 * @version 1.0
 *
 */
public interface ICartService {
	
	/**
	 * @apiNote Creates cart for user
	 * @param user
	 * @return cart
	 */
	Cart create(User user);
	
	/**
	 * @apiNote adds product to cartItem and updates cart total
	 * @param user
	 * @param cartRequest
	 * @return cart
	 */
	Cart addToCart(User user, CartRequest cartRequest);
	
	/**
	 * @apiNote removes cartItem from cart
	 * @param user
	 * @param cartRequest
	 * @return cart
	 */
	Cart removeFromCart(User user, CartRequest cartRequest);
	
	/**
	 * @apiNote finds cart by user returns cart
	 * @param user
	 * @return cart
	 */
	Cart findByUser(User user);
	
	/**
	 * @apiNote clears cart total
	 * @param user
	 * @return cart
	 */
	Cart clear(User user);	

}
