package com.shopping_service.security.jwt;

import org.springframework.security.core.Authentication;

import com.shopping_service.security.UserPrincipal;

import jakarta.servlet.http.HttpServletRequest;

public interface IJwtProvider {
	
	String generateJwtToken(UserPrincipal auth);
	
	Authentication getAuthenticated(HttpServletRequest req);

	boolean validateToken(HttpServletRequest req);

}
