package com.shopping_service.service.userconfirmationtoken;

import java.time.LocalDateTime;
import java.util.Optional;

import com.shopping_service.persistence.token.userconfirmationtoken.UserConfirmationToken;

public interface IUserConfirmationTokenService {
	
	/**	
	 * @param token
	 * @return UserConfirmationToken
	 */
	Optional<UserConfirmationToken> findByToken(String token);
	
	/**
	 * @param UserConfirmationToken
	 * @return UserConfirmationToken
	 */
	UserConfirmationToken save(UserConfirmationToken userConfirmationToken);

	/**
	 * @param token
	 * @param now
	 */
	void confirmedAt(String token, LocalDateTime now);

}
