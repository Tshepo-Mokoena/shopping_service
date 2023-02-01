package com.shopping_service.persistence.orderItem;

import java.math.BigDecimal;

import com.shopping_service.persistence.order.Order;
import com.shopping_service.persistence.product.Product;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter 
@Setter
@Table(name = "order_item")
public class OrderItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "_id", updatable = false, nullable = false, unique = true)
	private Long id;
	
	@OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinColumn(name = "product_id")
	private Product product;
	
	@Column(name = "qty", updatable = true, nullable = false)
	private Integer qty;
	
	@Column(name = "sub_total", updatable = true, nullable = false)
	private  BigDecimal subTotal;
	
	@ManyToOne
	@JoinColumn(name = "order_id", nullable = false, updatable = false)
	private Order order;
	
}
