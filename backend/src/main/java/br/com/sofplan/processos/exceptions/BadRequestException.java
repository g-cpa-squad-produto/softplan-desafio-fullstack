package br.com.sofplan.processos.exceptions;

import org.springframework.http.HttpStatus;

public class BadRequestException extends CustomException {

	private static final long serialVersionUID = -8462797069478262835L;

	public BadRequestException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }

    public BadRequestException() {
        super(HttpStatus.BAD_REQUEST, "Bad Request");
    }

}
