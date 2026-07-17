package com.example.school.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CourseNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CourseNotFoundException(Integer id) {
		super("Corso non trovato con id " + id);
	}

	public CourseNotFoundException(Integer id, Throwable cause) {
		super("Corso non trovato con id " + id, cause);
	}
}
