package com.shopping_service.persistence.loginhistory;

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
	
	@Column(name = "login_at", updatable = false, nullable = false)
	private LocalDateTime loginAt;
	
	@ManyToOne
	@JoinColumn(name = "user_id", updatable = false, nullable = false)
	private User user;

	public LocalDateTime getLoginAt() { return this.loginAt = LocalDateTime.now(); }	

}
