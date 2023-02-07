package com.shopping_service.persistence.token.passwordreset;

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
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Entity
@Getter
@Setter
@Table(name = "password_reset_token")
public class PasswordResetToken {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "_id", updatable = false, nullable = false, unique = true)
	private Long id;
	
	@Column(name = "token", updatable = false, nullable = false, unique = true)
	private String token;
	
	@Column(name = "created_at", updatable = false, nullable = false)
	private LocalDateTime createdAt;
	
	@Column(name = "expires_at", updatable = false, nullable = false)
	private LocalDateTime expiresAt;
	
	@Column(name = "reseted_at", updatable = false, nullable = true)
	private LocalDateTime resetedAt;
	
	@ManyToOne
	@JoinColumn(name = "user",nullable = false, updatable = false)
	private User user;

}
