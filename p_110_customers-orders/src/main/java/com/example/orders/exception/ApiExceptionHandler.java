package com.example.orders.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ApiExceptionHandler {
	
	@ExceptionHandler(DuplicateEmailException.class)
	public ResponseEntity<ErrorBody> gestisciDuplicateEmail(DuplicateEmailException exception, HttpServletRequest request) {
		HttpStatus status = HttpStatus.CONFLICT;
		ErrorBody body = ErrorBody.of(request.getRequestURI(), status, exception.getMessage());
		return ResponseEntity.status(status).body(body);
	}
	
	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<ErrorBody> gestisciCustomerNotFound(CustomerNotFoundException exception, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		ErrorBody body = ErrorBody.of(request.getRequestURI(), status, exception.getMessage());
		return ResponseEntity.status(status).body(body);
	}

}
