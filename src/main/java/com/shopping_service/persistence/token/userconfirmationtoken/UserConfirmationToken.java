package com.shopping_service.persistence.token.userconfirmationtoken;

import java.time.LocalDateTime;

import com.shopping_service.persistence.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
@Table(name = "user_confirmation_token")
public class UserConfirmationToken {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "_id", updatable = true, nullable = true, unique = true)
	private Long id;
	
	@Column(name = "token", updatable = false, nullable = false)
	private String token;
	
	@Column(name = "created_at", updatable = false, nullable = false)
	private LocalDateTime createdAt;
	
	@Column(name = "confirmed_at", updatable = false, nullable = false)
	private LocalDateTime confirmedAt;
	
	@Column(name = "expires_at", updatable = false, nullable = false)
	private LocalDateTime expiresAt;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false, updatable = false)
	private User user;

}
