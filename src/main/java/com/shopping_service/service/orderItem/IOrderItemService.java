package com.shopping_service.service.orderItem;

import java.util.List;

import com.shopping_service.persistence.order.Order;
import com.shopping_service.persistence.orderItem.OrderItem;

public interface IOrderItemService {
	
	/**
	 * @apiNote saves orderItem
	 * @param orderItem
	 */
	void save(OrderItem orderItem);
	
	/**
	 * @apiNote deletes orderItem
	 * @param orderItem
	 */
	void delete(OrderItem orderItem);
	
	/**
	 * @apiNote finds orderItem by order
	 * @param orderItem
	 * @return List<OrderItem>
	 */
	List<OrderItem> findByCart(Order order);

}
