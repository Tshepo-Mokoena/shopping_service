package com.shopping_service.security.service;

import static java.util.concurrent.TimeUnit.MINUTES;

import org.springframework.stereotype.Service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

@Service
public class LoginAttemptService {
	
	private static final int MAXIMUM_NUMBER_OF_ATTEMPTS = 5;

	private static final int ATTEMPT_INCREMENT = 1;

	private LoadingCache<String, Integer> loginAttemptCache;

	public LoginAttemptService() {
		loginAttemptCache = CacheBuilder.newBuilder().expireAfterWrite(15, MINUTES).maximumSize(100)
				.build(new CacheLoader<String, Integer>() {
					public Integer load(String key) {
						return 0;
					}
				});
	}

	public void evictAccountFromSignInAttemptCache(String email) {
		loginAttemptCache.invalidate(email);
	}

	public void addAccountToSignInAttemptCache(String email) {
		int attempts = 0;
		try {
			attempts = ATTEMPT_INCREMENT + loginAttemptCache.get(email);
		} catch (Exception e) {
			e.printStackTrace();
		}
		loginAttemptCache.put(email, attempts);
	}

	public boolean hasExceededMaxAttempts(String email) {
		try {
			return loginAttemptCache.get(email) >= MAXIMUM_NUMBER_OF_ATTEMPTS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}

