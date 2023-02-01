package com.shopping_service.security.util;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class SecurityUtil {	

	@Value("${salt}")
	private static String SALT;

	public static final String ROLE_PREFIX = "ROLE_";

	public static final String AUTH_HEADER = "authorization";

	public static final String AUTH_TOKEN_TYPE = "Bearer";

	public static final String AUTH_TOKEN_PREFIX = AUTH_TOKEN_TYPE + " ";

	public static SimpleGrantedAuthority convertToAuthority(String role) {		
		
		String formattedRole = role.startsWith(ROLE_PREFIX) ? role : ROLE_PREFIX + role;
		
		return new SimpleGrantedAuthority(formattedRole);
	}

	public static String extractAuthTokenFromRequest(HttpServletRequest req) {
		
		String bearerToken = req.getHeader(AUTH_HEADER);

		if (StringUtils.hasLength(bearerToken) && bearerToken.startsWith(AUTH_TOKEN_PREFIX))
			return bearerToken.substring(7);

		return null;
	}

	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	public static boolean validatePassword(String password) {
		
		boolean isUpperCase = false;
		
		boolean isLowerCase = false;
		
		boolean isDigit = false;		
        
		String specialChars = "(.*[@,#,$,%].*$)";
		
		if (password.length() < 8 || password.length() > 20) return false;		
		
		if (password.matches(specialChars)) return false;		
		
		char c;		
		
		for (int i = 0; 1 < password.length(); i++) {			
			
			c = password.charAt(i);			
			
			if (Character.isUpperCase(c))
				isUpperCase = true;			
			if (Character.isLowerCase(c))
				isLowerCase = true;			
			if (Character.isDigit(c))
				isDigit = true;			
			
			if(isDigit && isLowerCase && isUpperCase)
				return true;				
		}
		
		return false;		
	}
	
}
