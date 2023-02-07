package com.shopping_service.persistence.token.passwordreset;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordResetTokenRepository extends CrudRepository<PasswordResetToken, Long>{
	
	Optional<PasswordResetToken> findByToken(String token);

	@Modifying
	@Query("update PasswordResetToken set resetedAt = :resetedAt where token = :token")
	PasswordResetToken resetedAt(@Param("resetedAt") LocalDateTime resetedAt, @Param("token") String token);

}
