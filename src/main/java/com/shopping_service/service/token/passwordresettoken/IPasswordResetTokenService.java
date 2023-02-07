package com.shopping_service.service.token.passwordresettoken;

import java.time.LocalDateTime;
import java.util.Optional;

import com.shopping_service.persistence.token.passwordreset.PasswordResetToken;

public interface IPasswordResetTokenService {
	
	/**
	 * @apiNote saves  reset token
	 * @param passwordResetToken
	 * @return PasswordResetToken
	 */
	PasswordResetToken save(PasswordResetToken passwordResetToken);
	
	/**
	 * @apiNote finds token
	 * @param token
	 * @return PasswordResetToken
	 */
	Optional<PasswordResetToken> findByToken(String token);
	
	/**
	 * @apiNote reseted password at
	 * @param resetedAt
	 * @return PasswordResetToken
	 */
	PasswordResetToken resetedAt(LocalDateTime resetedAt, String token);	

}
