package com.shopping_service.client.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.shopping_service.json.response.Response;
import com.shopping_service.util.ExceptionMessages;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ExceptionResponse extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public Response<?> handleAllException(Exception ex) {
		log.warn(ex.getMessage());
		return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, ExceptionMessages.INTERNAL_SERVER_ERROR_MSG);
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	public Response<?> handleBadCredentialsException() {
		return buildResponse(HttpStatus.BAD_REQUEST, ExceptionMessages.INCORRECT_CREDENTIALS);
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public Response<?> handleAccessDeniedException() {
		return buildResponse(HttpStatus.FORBIDDEN, ExceptionMessages.ACCESS_DENIED_MESSAGE);
	}
	
	@ExceptionHandler(LockedException.class)
	public Response<?> handleLockedException() {
		return buildResponse(HttpStatus.FORBIDDEN, ExceptionMessages.ACCOUNT_LOCKED);
	}
	
	@ExceptionHandler(NoDataException.class)
	public Response<?> handleNoContentException(NoDataException ex) {
		return buildResponse(HttpStatus.NO_CONTENT, ex.getMessage());
	}

	@ExceptionHandler(NotFoundException.class)
	public Response<?> handleNotFoundException(NotFoundException ex) {
		return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
	}

	@ExceptionHandler(ConflictException.class)
	public Response<?> handleConflictException(ConflictException ex) {
		return buildResponse(HttpStatus.CONFLICT, ex.getMessage());
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Response.builder().status(HttpStatus.BAD_REQUEST)
				.message(ex.getMessage()).desc(ex.getBindingResult().toString()).build());
	}

	private Response<?> buildResponse(HttpStatus status, String message) {
		return Response.builder().status(status).message(message).build();
	}

}
