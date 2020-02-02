package br.com.sofplan.processos.exceptions;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends CustomException {

	private static final long serialVersionUID = -4810092482793802316L;

	public UnauthorizedException(String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }

    public UnauthorizedException() {
        super(HttpStatus.UNAUTHORIZED, "Unauthorized");
    }

}
