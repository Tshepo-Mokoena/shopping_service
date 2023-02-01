package com.shopping_service.persistence.loginhistory;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter @Setter
@Entity
@Table(name = "login_history")
public class LoginHistory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "_id", unique = true, updatable = false, nullable = false)
	private Long id;
	
	@Column(name = "description", updatable = false, nullable = false)
	private String desc;
	
	@Column(name = "created_at", updatable = false, nullable = false)
	private LocalDateTime createdAt;

}
