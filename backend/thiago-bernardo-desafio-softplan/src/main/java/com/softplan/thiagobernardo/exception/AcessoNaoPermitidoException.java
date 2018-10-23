package com.softplan.thiagobernardo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AcessoNaoPermitidoException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AcessoNaoPermitidoException() {
        super("Acesso não permitido!");
    }
	
	public AcessoNaoPermitidoException(String message) {
        super(message);
    }

    public AcessoNaoPermitidoException(String message, Throwable cause) {
        super(message, cause);
    }

}
