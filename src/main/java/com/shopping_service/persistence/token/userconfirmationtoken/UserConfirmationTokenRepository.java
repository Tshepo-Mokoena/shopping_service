package com.shopping_service.persistence.token.userconfirmationtoken;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserConfirmationTokenRepository extends CrudRepository<UserConfirmationToken, Long> {
	
	Optional<UserConfirmationToken> findByToken(String token);
	
	@Modifying
	@Query("update UserConfirmationToken set confirmedAt = :confirmedAt where token = :token")
	void confirmedAt(@Param("token") String token, @Param("confirmedAt") LocalDateTime confirmedAt);

}
