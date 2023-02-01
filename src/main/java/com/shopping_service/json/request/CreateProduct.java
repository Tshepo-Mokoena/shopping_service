package com.shopping_service.json.request;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class CreateProduct {
		
	@NotBlank(message = "title should atleast be 2 or more characters")
	@JsonProperty("title")	
	private String title;
	
	@JsonProperty("sub_title")
	private String subTitle;
	
	@NotBlank(message = "title should atleast be 2 or more characters")
	@JsonProperty("desc")
	private String desc;
	
	@NotNull
	@JsonProperty("qty")
	private Integer qty;
	
	@JsonProperty("price")
	private BigDecimal price;

}
