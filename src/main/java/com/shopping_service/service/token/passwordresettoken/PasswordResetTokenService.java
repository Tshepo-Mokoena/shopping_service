package com.shopping_service.service.token.passwordresettoken;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping_service.persistence.tokens.passwordreset.PasswordResetToken;
import com.shopping_service.persistence.tokens.passwordreset.PasswordResetTokenRepository;

@Service
public class PasswordResetTokenService implements IPasswordResetTokenService {
	
	@Autowired
	private PasswordResetTokenRepository passwordResetTokenRepository;

	@Override
	public PasswordResetToken save(PasswordResetToken passwordResetToken) {
		return passwordResetTokenRepository.save(passwordResetToken);
	}

	@Override
	public Optional<PasswordResetToken> findByToken(String token) {
		return passwordResetTokenRepository.findByToken(token);
	}

	@Override
	public PasswordResetToken resetedAt(LocalDateTime resetedAt,String token) {
		return passwordResetTokenRepository.resetedAt(resetedAt, token);
	}

}
