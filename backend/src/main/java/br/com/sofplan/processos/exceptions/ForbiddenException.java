package br.com.sofplan.processos.exceptions;

import org.springframework.http.HttpStatus;

public class ForbiddenException extends CustomException {

	private static final long serialVersionUID = -7701654337176626270L;

	public ForbiddenException(String message) {
        super(HttpStatus.FORBIDDEN, message);
    }

    public ForbiddenException() {
        super(HttpStatus.FORBIDDEN, "Forbidden");
    }

}
