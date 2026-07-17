package com.example.school.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PersonNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PersonNotFoundException(Integer id) {
		super("Persona non trovata con id " + id);
	}

	public PersonNotFoundException(Integer id, Throwable cause) {
		super("Persona non trovata con id " + id, cause);
	}
}
