package com.shopping_service.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.shopping_service.security.authority.Role;
import com.shopping_service.security.filter.ApiAuthenticationFilter;
import com.shopping_service.security.filter.JwtAuthorizationFilter;
import com.shopping_service.security.service.UserSecurityService;
import com.shopping_service.security.util.SecurityUtil;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Autowired
	private UserSecurityService userSecurityService;

	@Value("${authentication.api.key}")
	private String apiKey;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return SecurityUtil.passwordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.cors();

		http.csrf().disable();

		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.authorizeHttpRequests(
				(authorizeHttpRequests) -> authorizeHttpRequests.requestMatchers(Urls.publicUrls).permitAll()
						.requestMatchers(Urls.administrator).hasRole(Role.ADMINISTRATOR.name())
						.requestMatchers(Urls.superAdministrator).hasRole(Role.SUPER_ADMINISTRATOR.name())
						.requestMatchers(Urls.superAdministrator).hasRole(Role.SYSTEM_ADMINISTRATOR.name())
						.anyRequest()
						.authenticated())
				.userDetailsService(userSecurityService);
		http.addFilterBefore(jwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
				.addFilterBefore(apiAuthenticationFilter(), JwtAuthorizationFilter.class);

		return http.build();
	}

	@Bean
	public ApiAuthenticationFilter apiAuthenticationFilter() {
		return new ApiAuthenticationFilter(apiKey);
	}

	@Bean
	public JwtAuthorizationFilter jwtAuthorizationFilter() {
		return new JwtAuthorizationFilter();
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*").allowedMethods("*").allowedHeaders("*");
			}

		};
	}

}
