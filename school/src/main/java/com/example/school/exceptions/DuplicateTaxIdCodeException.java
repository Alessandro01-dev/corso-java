package com.example.school.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateTaxIdCodeException extends RuntimeException{

private static final long serialVersionUID = 1L;
	
	public DuplicateTaxIdCodeException(String taxIdCode) {
		super("Codice fiscale " + taxIdCode + " già presente in archivio ");
	}

	public DuplicateTaxIdCodeException(Throwable cause) {
		super("Codice fiscale già presente in archivio", cause);
	}

}
