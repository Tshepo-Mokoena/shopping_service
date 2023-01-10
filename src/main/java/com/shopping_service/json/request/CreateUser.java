package com.shopping_service.json.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * 
 * @author Tshepo W Mokoena
 * @version 1.0
 *
 */
@SuperBuilder
@Getter 
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateUser {
	
	@JsonProperty("first_name")
	private String firstName;
	
	@JsonProperty("last_name")
	private String lastName;
	
	@JsonProperty("email")
	private String email;

}
