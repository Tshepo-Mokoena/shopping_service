package com.shopping_service.service.cart;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopping_service.client.exception.NotFoundException;
import com.shopping_service.json.request.CartRequest;
import com.shopping_service.persistence.cart.Cart;
import com.shopping_service.persistence.cart.CartRepository;
import com.shopping_service.persistence.cartitem.CartItem;
import com.shopping_service.persistence.product.Product;
import com.shopping_service.persistence.user.User;
import com.shopping_service.service.cartItem.ICartItemService;
import com.shopping_service.service.product.IProductService;

@Service
public class CartService implements ICartService {
	
	@Autowired	
	private CartRepository cartRepo;
	
	@Autowired	
	private ICartItemService cartItemService;
	
	@Autowired
	private IProductService productService;

	@Override
	public Cart create(User user) {
		Cart cart = cartRepo.save(Cart.builder().user(user)
				.createdAt(LocalDateTime.now()).total(new BigDecimal(0)).build());
		return cart;
	}

	@Override
	@Transactional
	public Cart addToCart(User user, CartRequest cartRequest) {
		
		Cart cart = cartRepo.findByUser(user).orElseThrow(() -> new NotFoundException("cart_not_found"));
		
		if (cartRequest.getQty() < 1) { cartRequest.setQty(1); }
		
		Product product = productService.findByProductId(cartRequest.getProductId())
				.orElseThrow(() -> new NotFoundException("product_not_found: ".concat(cartRequest.getProductId())));
		
		List<CartItem> cartItems = cartItemService.findByCart(cart);
		
		if (!cartItems.isEmpty()) {
			
			for (CartItem cartItem: cartItems) {
				
				if (cartItem.getProduct().getProductId().contains(product.getProductId())) {
					
					if(cartRequest.getQty() >= cartItem.getProduct().getQty())
						cartRequest.setQty(cartItem.getProduct().getQty());
					
					cartItem.setQty(cartRequest.getQty());
					
					cartItem.setSubTotal(
							cartItem.getProduct().getPrice().multiply(new BigDecimal(cartRequest.getQty()))
							);
					
					cartItemService.save(cartItem);
					
					Cart savedCart = cartRepo.save(caculateCartTotal(cart, cartItemService.findByCart(cart)));
					
					return savedCart;
					
				}
				
			}
			
		}
		
		CartItem newCartItem = CartItem.builder()
				.cart(cart)
				.product(product)
				.qty(cartRequest.getQty())
				.subTotal(product.getPrice().multiply(new BigDecimal(cartRequest.getQty())))
				.build();
		
		cartItemService.save(newCartItem);
		
		cart.setCreatedAt(LocalDateTime.now());
		
		Cart savedCart = cartRepo.save(caculateCartTotal(cart, cartItemService.findByCart(cart)));

		return savedCart;
	}

	@Override
	public Cart findByUser(User user) {
		return cartRepo.findByUser(user).orElseThrow(() -> new NotFoundException("cart_not_found"));
	}

	@Override
	@Transactional
	public Cart clear(User user) {
		
		Cart cart = cartRepo.findByUser(user).orElseThrow( () -> new NotFoundException("cart_not_found") );
		
		for (CartItem cartItem : cartItemService.findByCart(cart)) {
			
			cartItemService.delete(cartItem);
			
		}
		
		cart.setTotal(new BigDecimal(0));
		
		cart.setUpdatedAt(LocalDateTime.now());
		
		return cartRepo.save(cart);
	}
	
	private Cart caculateCartTotal(Cart cart, List<CartItem> cartItems) {
		
		BigDecimal cartTotal = new BigDecimal(0);
		
		for (CartItem cartItem: cartItems) {
			
			cartTotal = cartTotal.add(cartItem.getSubTotal());
			
		}
		
		cart.setUpdatedAt(LocalDateTime.now());
		
		return Cart.builder().total(cartTotal).build();
	}

	@Override
	@Transactional
	public Cart removeFromCart(User user, CartRequest cartRequest) {
		
		Cart cart = findByUser(user);
		
		Product product = productService.findByProductId(cartRequest.getProductId())
				.orElseThrow(() -> new NotFoundException("product_not_found: ".concat(cartRequest.getProductId())));
		
		List<CartItem> cartItems = cartItemService.findByCart(cart);
		
		for (CartItem cartItem : cartItems) {
			
			if (cartItem.getProduct().getProductId().contains(product.getProductId()))
				cartItemService.delete(cartItem);
			
		}		
		
		Cart savedCart = cartRepo.save(caculateCartTotal(cart, cartItemService.findByCart(cart)));
		
		return savedCart;
	}

}
