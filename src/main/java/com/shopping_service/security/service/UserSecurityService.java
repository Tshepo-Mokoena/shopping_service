package com.shopping_service.security.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shopping_service.persistence.user.User;
import com.shopping_service.security.UserPrincipal;
import com.shopping_service.security.util.SecurityUtil;
import com.shopping_service.service.user.IUserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserSecurityService implements UserDetailsService {
	
	@Autowired
	private IUserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		User user = userService.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("wrong_password_or_email"));
		
		Set<GrantedAuthority> authority = Set.of(SecurityUtil.convertToAuthority(user.getRole().name()));
	
		UserPrincipal userPrincipal = UserPrincipal.builder()
				.id(user.getId()).user(user).authorities(authority).build();
		
		log.info(userPrincipal.toString());
		
		return userPrincipal;
	}

}
