package com.shopping_service.service.orderItem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping_service.persistence.order.Order;
import com.shopping_service.persistence.orderItem.OrderItem;
import com.shopping_service.persistence.orderItem.OrderItemRepository;

@Service
public class OrderItemService implements IOrderItemService {
	
	@Autowired
	private OrderItemRepository orderItemRepo;

	@Override
	public void save(OrderItem orderItem) {
		orderItemRepo.save(orderItem);
	}

	@Override
	public void delete(OrderItem orderItem) {
		orderItemRepo.delete(orderItem);
	}

	@Override
	public List<OrderItem> findByCart(Order order) {
		return orderItemRepo.findByOrder(order);
	}	

}
