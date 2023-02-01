package com.shopping_service.service.order;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.shopping_service.persistence.order.Order;
import com.shopping_service.persistence.user.User;

/** 
 * @author Tshepo W Mokoena
 * @version 1.0
 */
public interface IOrderService {

	/**
	 * @apiNote creates new order
	 * @param Email
	 * @return Order
	 */
	Order create(User user);
	
	/**
	 * @apiNote finds orders by user
	 * @param user
	 * @param page
	 * @param pageSize
	 * @return Page<Order>
	 */
	Page<Order> findByUser(User user, Integer page, Integer pageSize);
	
	/**
	 * @apiNote finds all orders
	 * @param page
	 * @param pageSize
	 * @return
	 */
	Page<Order> findAll(Integer page, Integer pageSize);
	
	/**
	 * @apiNote finds Order by id
	 * @param id
	 * @return Optional<Order>
	 */
	Optional<Order> findById(Long id);

}
