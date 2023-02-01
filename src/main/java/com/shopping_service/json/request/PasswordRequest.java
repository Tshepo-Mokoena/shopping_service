package com.shopping_service.json.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/** 
 * @author Tshepo Mokoena
 * @version 1.0
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PasswordRequest {
	
	@JsonProperty
	private String password;
	
	@JsonProperty
	private String confirmPassword;

}
