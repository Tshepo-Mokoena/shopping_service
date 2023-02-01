package com.shopping_service.persistence.orderItem;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shopping_service.persistence.order.Order;

@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem, Long> {
	
	/**
	 * @apiNote finds orderItem by order
	 * @param order
	 * @return order
	 */
	List<OrderItem> findByOrder(Order order);

}
