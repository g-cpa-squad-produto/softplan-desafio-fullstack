package br.com.softplan.processmanagement.services.exceptions;

public class EmailAlreadyUsedException extends RuntimeException {

    public EmailAlreadyUsedException(String message) {
        super(message);
    }

    public EmailAlreadyUsedException(String message, Throwable cause) {
        super(message, cause);
    }

}
