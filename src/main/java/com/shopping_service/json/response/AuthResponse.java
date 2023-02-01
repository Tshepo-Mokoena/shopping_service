package com.shopping_service.json.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shopping_service.persistence.user.User;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@NoArgsConstructor
public class AuthResponse {
	
	@JsonProperty("first_name")
	private String firstName;
	
	@JsonProperty("last_name")
	private String lastName;
	
	@JsonProperty("phone")
	private String phone;
	
	@JsonProperty("email")
	private String email;
	
	@JsonProperty("token")
	private String token;
	
	private transient User user;

	public String getFirstName() { return firstName = this.getUser().getFirstName(); }

	public String getLastName() { return lastName = this.getUser().getLastName(); }

	public String getPhone() { return phone = this.getUser().getPhone(); }

	public String getEmail() { return email = this.getUser().getEmail(); }

	public String getToken() { return token = this.getUser().getToken(); }	

}
