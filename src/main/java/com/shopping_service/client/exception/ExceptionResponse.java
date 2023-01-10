package com.shopping_service.client.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.shopping_service.json.response.Response;

@RestControllerAdvice
public class ExceptionResponse extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public Response<?> handleAllException(Exception ex){
		return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
	}
	
	@ExceptionHandler(NoDataException.class)
	public Response<?> handleNoContentException(NoDataException ex){
		return buildResponse(HttpStatus.NO_CONTENT, ex.getMessage());
	}
	
	@ExceptionHandler(NotFoundException.class)
	public Response<?> handleNotFoundException(NotFoundException ex){
		return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
	}
	
	@ExceptionHandler(ConflictException.class)
	public Response<?> handleConflictException(ConflictException ex){
		return buildResponse(HttpStatus.CONFLICT, ex.getMessage());
	}
	
	public Response<?> buildResponse(HttpStatus status, String message){
		return Response.builder()
				.status(status)
				.message(message)
				.build();
	}

}
