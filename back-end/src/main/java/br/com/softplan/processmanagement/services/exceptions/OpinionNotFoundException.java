package br.com.softplan.processmanagement.services.exceptions;

public class OpinionNotFoundException extends RuntimeException {
    public OpinionNotFoundException(String message) {
        super(message);
    }

    public OpinionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
