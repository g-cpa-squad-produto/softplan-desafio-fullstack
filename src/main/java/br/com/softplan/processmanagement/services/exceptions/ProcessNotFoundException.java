package br.com.softplan.processmanagement.services.exceptions;

public class ProcessNotFoundException extends RuntimeException {

    public ProcessNotFoundException(String message) {
        super(message);
    }

    public ProcessNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
