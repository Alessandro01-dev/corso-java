package com.example.school.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MaxEnrolledStudentNumberReached extends RuntimeException  {

private static final long serialVersionUID = 1L;

	public MaxEnrolledStudentNumberReached(Integer id) {
		super("Corso con id" + id + "ha raggiunto il numero massimo di studenti");
	}

	public MaxEnrolledStudentNumberReached(Integer id, Throwable cause) {
		super("Corso con id " + id + " ha raggiunto il numero massimo di studenti", cause);
	}

}
