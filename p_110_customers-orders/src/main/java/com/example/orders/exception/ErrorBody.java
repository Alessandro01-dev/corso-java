package com.example.orders.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

public record ErrorBody(
		LocalDateTime timestamp, 
		int status, 
		String error, 
		String exceptionMessage, 
		String path) {

	public static ErrorBody of(
			String path, 
			HttpStatus status, 
			String message) {
		return new ErrorBody(
				LocalDateTime.now(), 
				status.value(), 
				status.getReasonPhrase(), 
				message, 
				path);
	}
}
