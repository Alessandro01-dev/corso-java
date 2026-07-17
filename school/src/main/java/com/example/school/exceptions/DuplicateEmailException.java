package com.example.school.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateEmailException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public DuplicateEmailException(String email) {
		super("Email " + email + " già esistente in archivio ");
	}

	public DuplicateEmailException(Throwable cause) {
		super("Email già esistente in archivio ", cause);
	}

}
