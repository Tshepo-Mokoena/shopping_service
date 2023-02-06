package com.shopping_service.security.filter;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.shopping_service.security.UserPrincipal;
import com.shopping_service.security.util.SecurityUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApiAuthenticationFilter extends OncePerRequestFilter {

	private String accessKey;

	public ApiAuthenticationFilter(String accessKey) {
		this.accessKey = accessKey;
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException{
		return !request.getRequestURI().startsWith("/api/system");
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		try {

			String requestKey = SecurityUtil.extractAuthTokenFromRequest(request);

			if (requestKey == null || !requestKey.equals(accessKey)) {
				log.warn("admin endpoint rquested without/wrong key uri {}");
				throw new RuntimeException("admin endpoint rquested without/wrong key uri {}");
			}

			UserPrincipal user = UserPrincipal.createdAdmin();
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null,
					user.getAuthorities());

			SecurityContextHolder.getContext().setAuthentication(authentication);

		} catch (Exception e) {
			logger.error("could not set account auth in security context", e);
		}

		filterChain.doFilter(request, response);
	}

	
}
