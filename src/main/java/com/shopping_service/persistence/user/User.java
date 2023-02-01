package com.shopping_service.persistence.user;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shopping_service.security.authority.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 
 * @author Tshepo W Mokoena
 * @version 1.0
 *
 */
@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "application_users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	@Column(name = "_id", nullable = false, updatable = false, unique = true)
	private Long id;
	
	@Column(name = "user_id", nullable = false, updatable = false, unique = true)
	private String userId;
	
	@Column(name = "first_name", nullable = false, updatable = true, length = 50)
	private String firstName;
	
	@Column(name = "last_name", nullable = false, updatable = true, length = 50)
	private String lastName;
	
	@Column(name = "phone", nullable = false, updatable = true, length = 50)
	private String phone;
	
	@Column(name = "password", nullable = false, updatable = true, length = 50)
	private String password;
	
	@Email
	@Column(name = "email", nullable = false, updatable = false, unique = true)
	private String email;
	
	@Column(name = "active", nullable = false, updatable = true)
	private boolean active;
	
	@Column(name = "locked", nullable = false, updatable = true)
	private boolean locked;
	
	@Column(name = "updated_at", nullable = false, updatable = true)
    private LocalDateTime updatedAt;
	
	@Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;
	
    @Column(name = "last_login", nullable = true, updatable = true)
    private Date lastLogin;
	
	@Column(name = "logins_count", nullable = true, updatable = true)
    private Integer loginsCount;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "role", nullable = false, updatable = true)
	private Role role;
	
	@Transient
	private String token;
		
	/**
	 * 
	 * @return local date & time
	 */
	public LocalDateTime getUpdatedAt() { return this.updatedAt = LocalDateTime.now(); }
	
	/**
	 *  
	 * @return current local date & time
	 */
	public Date getCreatedAt() { return this.createdAt = new Date(); }

	/**
	 * 
	 * @return generates random UUID for User Id
	 */
	public String getUserId() { return this.userId = UUID.randomUUID().toString(); }

}
