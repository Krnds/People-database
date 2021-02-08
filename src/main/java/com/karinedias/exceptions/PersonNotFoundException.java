package com.karinedias.exceptions;

public class PersonNotFoundException extends IllegalArgumentException {

	private static final long serialVersionUID = 1L;

	public PersonNotFoundException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}
}
