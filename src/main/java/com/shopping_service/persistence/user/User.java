package com.shopping_service.persistence.user;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * 
 * @author Tshepo W Mokoena
 * @version 1.0
 *
 */
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "_id", nullable = false, updatable = false, unique = true)
	private Long id;
	
	@Column(name = "user_id", nullable = false, updatable = false, unique = true)
	private String userId;
	
	@Column(name = "first_name", nullable = false, updatable = false, length = 50)
	private String firstName;
	
	@Column(name = "last_name", nullable = false, updatable = false, length = 50)
	private String lastName;
	
	@Email
	@Column(name = "email", nullable = false, updatable = false, unique = true)
	private String email;
	
	@Column(name = "updated_at", nullable = false, updatable = false)
    private LocalDateTime updatedAt;
	
	@Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
	
	/**
	 * 
	 * @return current local date & time
	 */
	public LocalDateTime getCreatedAt() {
		return LocalDateTime.now();
	}

}
