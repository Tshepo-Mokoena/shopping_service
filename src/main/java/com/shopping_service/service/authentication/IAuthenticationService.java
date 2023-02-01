package com.shopping_service.service.authentication;

import com.shopping_service.json.request.AuthRequest;
import com.shopping_service.json.request.PasswordRequest;
import com.shopping_service.persistence.user.User;

public interface IAuthenticationService {

	User signIn(AuthRequest authRequest);

	String forgotPassword(String email);

	String changePassword(String token, PasswordRequest passwordRequest);

}
