package com.shopping_service.service.cartItem;

import java.util.List;

import com.shopping_service.persistence.cart.Cart;
import com.shopping_service.persistence.cartitem.CartItem;

/** 
 * @author Tshepo W Mokoena
 * @version 1.0 *
 */
public interface ICartItemService {
	
	/**
	 * @apiNote saves cartItem
	 * @param cartItem
	 */
	void save(CartItem cartItem);
	
	/**
	 * @apiNote deletes cartItem
	 * @param cartItem
	 */
	void delete(CartItem cartItem);
	
	/**
	 * @apiNote finds cartItem by cart
	 * @param cart
	 * @return List<CartItem>
	 */
	List<CartItem> findByCart(Cart cart);

}
