package com.shopping_service.service.token.userconfirmationtoken;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping_service.persistence.token.userconfirmationtoken.UserConfirmationToken;
import com.shopping_service.persistence.token.userconfirmationtoken.UserConfirmationTokenRepository;

@Service
public class UserConfirmationTokenService implements IUserConfirmationTokenService {
	
	@Autowired
	private UserConfirmationTokenRepository userConfirmationTokenRepo;

	@Override
	public Optional<UserConfirmationToken> findByToken(String token) {
		return userConfirmationTokenRepo.findByToken(token);
	}

	@Override
	public UserConfirmationToken save(UserConfirmationToken userConfirmationToken) {
		return userConfirmationTokenRepo.save(userConfirmationToken);
	}

	@Override
	public void confirmedAt(String token, LocalDateTime now) {
		userConfirmationTokenRepo.confirmedAt(token, now);		
	}

}
