package com.example.school.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ApiExceptionHandler {
	
	@ExceptionHandler(DuplicateEmailException.class)
	public ResponseEntity<ErrorBody> DuplicateEmailHandler(DuplicateEmailException exception, HttpServletRequest request) {
		HttpStatus status = HttpStatus.CONFLICT;
		ErrorBody body = ErrorBody.of(request.getRequestURI(), status, exception.getMessage());
		return ResponseEntity.status(status).body(body);
	}
	
	@ExceptionHandler(PersonNotFoundException.class)
	public ResponseEntity<ErrorBody> PersonNotFoundHandler(PersonNotFoundException exception, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		ErrorBody body = ErrorBody.of(request.getRequestURI(), status, exception.getMessage());
		return ResponseEntity.status(status).body(body);
	}
	
	@ExceptionHandler(CourseNotFoundException.class)
	public ResponseEntity<ErrorBody> CourseNotFoundHandler(PersonNotFoundException exception, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		ErrorBody body = ErrorBody.of(request.getRequestURI(), status, exception.getMessage());
		return ResponseEntity.status(status).body(body);
	}

}
