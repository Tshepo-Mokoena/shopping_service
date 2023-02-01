package com.shopping_service.persistence.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.shopping_service.persistence.orderItem.OrderItem;
import com.shopping_service.persistence.user.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name  = "orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "_id", updatable = false, nullable = false, unique = true)
	private Long id;
	
	@OneToMany(mappedBy="order", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private List<OrderItem> orderItems;
	
	@Column(name = "total", updatable = true, nullable = false)
	private BigDecimal total;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(name = "updated_at", updatable = true, nullable = true)
	private LocalDateTime updatedAt;
	
	@Column(name = "created_at", updatable = false, nullable = false)
	private LocalDateTime createdAt;

}
