package com.shopping_service.service.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopping_service.client.exception.ConflictException;
import com.shopping_service.persistence.cart.Cart;
import com.shopping_service.persistence.cartitem.CartItem;
import com.shopping_service.persistence.order.Order;
import com.shopping_service.persistence.order.OrderRespository;
import com.shopping_service.persistence.orderItem.OrderItem;
import com.shopping_service.persistence.user.User;
import com.shopping_service.service.cart.ICartService;
import com.shopping_service.service.cartItem.ICartItemService;
import com.shopping_service.service.orderItem.IOrderItemService;

import lombok.Synchronized;

@Service
public class OrderService implements IOrderService {
	
	@Autowired
	private OrderRespository orderRepo;
	
	@Autowired
	private IOrderItemService orderItemService;
	
	@Autowired
	private ICartService cartService;
	
	@Autowired
	private ICartItemService cartItemService;

	
	@Synchronized
	@Transactional
	@Override
	public Order create(User user) {
		
		Cart cart = cartService.findByUser(user);
		
		List<CartItem> cartItems = cartItemService.findByCart(cart);
		
		Order order = orderRepo.save(Order.builder()
				.createdAt(LocalDateTime.now())
				.total(new BigDecimal(0))
				.user(user)
				.build());
		
		BigDecimal orderTotal = new BigDecimal(0); 
		
		for (CartItem cartItem : cartItems) {
			
			if ( cartItem.getQty() > cartItem.getProduct().getQty() )
				throw new ConflictException("product_qty_is_less_than_required:".concat(cartItem.getQty().toString()));
			
			OrderItem orderItem = OrderItem.builder()
					.order(order)
					.product(cartItem.getProduct())
					.qty(cartItem.getQty())
					.subTotal( cartItem.getProduct().getPrice().multiply(new BigDecimal(cartItem.getQty())) )
					.build();
			
			orderTotal = orderTotal.add(orderItem.getSubTotal());
			
			orderItemService.save(orderItem);
			
		}
		
		orderRepo.updateTotal(orderTotal, order.getId());
		
		order.setTotal(orderTotal);
		
		cartService.clear(user);		
		
		return order;
	}

	
	@Override
	public Page<Order> findByUser(User user, Integer page, Integer pageSize) {
		return orderRepo.findByUser(user, PageRequest.of(page, pageSize));
	}


	@Override
	public Page<Order> findAll(Integer page, Integer pageSize) {
		return orderRepo.findAll(PageRequest.of(page, pageSize));
	}


	@Override
	public Optional<Order> findById(Long id) {
		return orderRepo.findById(id);
	}

}
