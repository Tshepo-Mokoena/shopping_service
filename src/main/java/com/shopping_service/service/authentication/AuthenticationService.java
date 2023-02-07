package com.shopping_service.service.authentication;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.shopping_service.client.exception.ConflictException;
import com.shopping_service.client.exception.NotFoundException;
import com.shopping_service.json.request.AuthRequest;
import com.shopping_service.json.request.PasswordRequest;
import com.shopping_service.persistence.loginhistory.LoginHistory;
import com.shopping_service.persistence.token.passwordreset.PasswordResetToken;
import com.shopping_service.persistence.user.User;
import com.shopping_service.security.UserPrincipal;
import com.shopping_service.security.jwt.IJwtProvider;
import com.shopping_service.security.util.SecurityUtil;
import com.shopping_service.service.loginhistory.ILoginHistoryService;
import com.shopping_service.service.token.passwordresettoken.IPasswordResetTokenService;
import com.shopping_service.service.user.IUserService;
import com.shopping_service.util.Util;

@Service
public class AuthenticationService implements IAuthenticationService {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private IJwtProvider jwtProvider;
	
	@Autowired
	private IPasswordResetTokenService passwordResetTokenService;
	
	@Autowired
	private ILoginHistoryService loginHistoryService;

	@Autowired
	private IUserService userService;

	@Override
	public User signIn(AuthRequest authRequest) {

		Authentication authentication = 
				authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
		
		UserPrincipal accountPrincipal = (UserPrincipal) authentication.getPrincipal();
		
		String jwtToken = jwtProvider.generateJwtToken(accountPrincipal);
				
		User signedInUser = accountPrincipal.getUser();
		
		loginHistoryService.save(LoginHistory.builder()
				.user(signedInUser)
				.desc("success")
				.build());
		
		signedInUser.setLastLogin(new Date());
		
		signedInUser.setLoginsCount(signedInUser.getLoginsCount() + 1);
		
		signedInUser.setUpdatedAt(LocalDateTime.now());
		
		userService.save(signedInUser);
		
		signedInUser.setToken(jwtToken);
		
		return signedInUser;
	}

	@Override
	public String forgotPassword(String email) {
		
		User user = userService.findByEmail(email)
				.orElseThrow(() -> new NotFoundException("email_not_found: ".concat(email)));
		
		String token = Util.generateUniqueNumericUUId();
		
		boolean uniqueToken = false;
		
		//TODO
		while (uniqueToken) {
			if ( !passwordResetTokenService.findByToken(token).isEmpty() )
				token = Util.generateUniqueNumericUUId();
			else
				uniqueToken = true;
		}		
		
		PasswordResetToken passwordResetToken = PasswordResetToken.builder()
				.token(token)
				.user(user)
				.createdAt(LocalDateTime.now())
				.expiresAt(LocalDateTime.now().plusDays(7))
				.build();
		
		passwordResetTokenService.save(passwordResetToken);
		
		return "token_sent_to_email";
	}

	@Override
	public String changePassword(String token, PasswordRequest passwordRequest) {
		
		PasswordResetToken passwordResetToken = passwordResetTokenService.findByToken(token)
				.orElseThrow(() -> new NotFoundException("token_invalid: ".concat(token)));
		
		if ( passwordResetToken.getExpiresAt().isBefore(LocalDateTime.now()) )
			throw new NotFoundException("token_invalid: ".concat(token));
		
		if (!passwordRequest.getPassword().contains(passwordRequest.getConfirmPassword()))
			throw new ConflictException("password_mismatch");
		
		SecurityUtil.validatePassword(passwordRequest.getPassword());
		
		User user = passwordResetToken.getUser();
		
		user.setPassword(SecurityUtil.passwordEncoder().encode(passwordRequest.getPassword()));
		
		user.setUpdatedAt(LocalDateTime.now());
		
		userService.save(user);
		
		return null;
	}

}
