package com.shopping_service.service.cartItem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping_service.persistence.cart.Cart;
import com.shopping_service.persistence.cartitem.CartItem;
import com.shopping_service.persistence.cartitem.CartItemRepository;

@Service
public class CartItemService implements ICartItemService {	

	@Autowired	
	private CartItemRepository cartItemRepo;

	@Override
	public void save(CartItem cartItem) {
		cartItemRepo.save(cartItem);
	}

	@Override
	public void delete(CartItem cartItem) {
		cartItemRepo.delete(cartItem);		
	}

	@Override
	public List<CartItem> findByCart(Cart cart) {
		return cartItemRepo.findByCart(cart);
	}
	

}
