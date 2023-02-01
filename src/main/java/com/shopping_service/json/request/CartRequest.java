package com.shopping_service.json.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartRequest {
	
	@Size(min = 12, max = 12, message = "product_id_should_not_be_null")
	@NotBlank(message = "product_id_should_not_be_null")
	@JsonProperty	
	private String productId;

	@NotNull(message = "product_qty_should_not_be_null")
	@JsonProperty
	private int qty;

}
