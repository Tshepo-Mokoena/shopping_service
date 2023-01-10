package com.shopping_service.client.exception;

@SuppressWarnings("serial")
public class NotFoundException extends RuntimeException{
	
	public NotFoundException(String message) {
		super(message);
	}

}
