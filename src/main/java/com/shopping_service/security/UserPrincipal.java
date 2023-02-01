package com.shopping_service.security;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.shopping_service.persistence.user.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
public class UserPrincipal implements UserDetails {
	
	private Long id;
	
	private String email;	
	
	private transient String password;
	
	private Set<GrantedAuthority> authorities;
	
	private boolean active;
	
	private boolean locked;
	
	private transient User user;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.password = this.getUser().getPassword();
	}

	@Override
	public String getUsername() {
		return this.getUser().getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !this.getUser().isLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.getUser().isActive();
	}

	public static UserPrincipal createdAdmin() {
		
		return null;
	}

}
