package br.com.sofplan.processos.exceptions;

import org.springframework.http.HttpStatus;

public class ConflictException extends CustomException {

	private static final long serialVersionUID = 1L;

	public ConflictException(String message) {
        super(HttpStatus.CONFLICT, message);
    }

    public ConflictException() {
        super(HttpStatus.CONFLICT, "Conflict");
    }

}
