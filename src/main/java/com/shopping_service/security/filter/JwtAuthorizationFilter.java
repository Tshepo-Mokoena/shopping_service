package com.shopping_service.security.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.shopping_service.security.jwt.IJwtProvider;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthorizationFilter extends OncePerRequestFilter {

	private IJwtProvider jwtProvider;
	
	@Autowired
	private void setJwtAuthorizationFilter(IJwtProvider jwtProvider) {
		this.jwtProvider = jwtProvider;
	}
	
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException{
		return request.getRequestURI().startsWith("/api/auth/admin");
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		Authentication authentication = jwtProvider.getAuthenticated(request);
		
		if( authentication != null && jwtProvider.validateToken(request))
			SecurityContextHolder.getContext().setAuthentication(authentication);
				
		filterChain.doFilter(request, response);
	}

}
