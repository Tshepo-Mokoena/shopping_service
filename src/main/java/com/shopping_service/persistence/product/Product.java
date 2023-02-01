package com.shopping_service.persistence.product;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	@Column(name = "_id", updatable = false, nullable = false, unique = true)
	private Long id;
	
	@Column(name = "product_id", updatable = false, nullable = false, unique = true)
	private String productId;
	
	@Column(name = "title", updatable = true, nullable = false, unique = true)
	private String title;
	
	@Column(name = "sub_title", updatable = true, nullable = true)
	private String subTitle;
	
	@Column(name = "description", updatable = true, nullable = true)
	private String desc;
	
	@Column(name = "qty", updatable = true, nullable = false)
	private Integer qty;
	
	@Column(name = "price", updatable = true, nullable = false)
	private BigDecimal price;
		
	@Column(name = "expected_revenue", updatable = true, nullable = false)
	private BigDecimal revenue;
	
	@Column(name = "updated_at", updatable = true, nullable = true)
	private Date updatedAt;
	
	@Column(name = "created_at", updatable = true, nullable = true)
	private Date createdAt;
	
	public String getProductId() {
		return this.productId = UUID.randomUUID().toString();
	}
	
	public Date getCreatedAt() {
		return this.createdAt = new Date();
	}
	
}
