package com.shopping_service.security.jwt;

import java.util.Arrays;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.shopping_service.security.UserPrincipal;
import com.shopping_service.security.util.SecurityUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtProvider implements IJwtProvider {

	@Value("${jwt.secret}")
	private String JWT_SECRET;
	
	@Value("${jwt.expiration-in-ms}")
	private Long JWT_EXPIRATION_IN_MS;
		
	@Override
	public String generateJwtToken(UserPrincipal auth) {
		String authorities = auth.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));
		
		return Jwts.builder()
				.setSubject(auth.getUsername())
				.claim("roles", authorities)
				.claim("accountId", auth.getId())
				.setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_IN_MS))
				.signWith(SignatureAlgorithm.HS512, JWT_SECRET)
				.compact();
	}

	@Override
	public Authentication getAuthenticated(HttpServletRequest req) {
		Claims claims = extractClaims(req);
		
		if(claims == null)
			return null;			
		
		String email = claims.getSubject();
		Long accountId = claims.get("accountId", Long.class);
		
		Set<GrantedAuthority> authorities = Arrays.stream(claims.get("roles").toString().split(","))
				.map(SecurityUtil::convertToAuthority)
				.collect(Collectors.toSet());
				 
		UserDetails userDetails = UserPrincipal.builder()
				.email(email)
				.authorities(authorities)
				.id(accountId)
				.build();
		
		if(email == null)
			return null;		
		
		return new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
	}

	@Override
	public boolean validateToken(HttpServletRequest req) {
		Claims claims = extractClaims(req);
		
		if(claims == null)
			return false;		
		
		if(claims.getExpiration().before(new Date()))
			return false;		
		
		return true;
	}

	private Claims extractClaims(HttpServletRequest req){

		String token = SecurityUtil.extractAuthTokenFromRequest(req);
		
		if(token == null)
			return null;		
		
		return Jwts.parser()
				.setSigningKey(JWT_SECRET)
				.parseClaimsJws(token)
				.getBody();		
	}

}
