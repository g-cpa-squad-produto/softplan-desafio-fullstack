package com.softplan.thiagobernardo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ParecerProcessoException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ParecerProcessoException() {
        super("Já existe um parecer para esse processo!");
    }
	
	public ParecerProcessoException(String message) {
        super(message);
    }

    public ParecerProcessoException(String message, Throwable cause) {
        super(message, cause);
    }

}
