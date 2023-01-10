package com.shopping_service.client.exception;

@SuppressWarnings("serial")
public class ConflictException extends RuntimeException{
	
	public ConflictException(String message) {
		super(message);
	}

}
