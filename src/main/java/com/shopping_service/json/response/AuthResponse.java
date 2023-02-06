package com.shopping_service.json.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shopping_service.persistence.user.User;
import com.shopping_service.security.authority.Role;

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
	
	@JsonProperty("role")
	private Role role;
	
	@JsonProperty("token")
	private String token;
	
	private transient User user;

	public String getFirstName() { return this.firstName = this.getUser().getFirstName(); }

	public String getLastName() { return this.lastName = this.getUser().getLastName(); }

	public String getPhone() { return this.phone = this.getUser().getPhone(); }

	public String getEmail() { return this.email = this.getUser().getEmail(); }
	
	public Role getRole() { return this.role = this.getUser().getRole(); }

	public String getToken() { return this.token = this.getUser().getToken(); }	

}
