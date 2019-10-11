package br.com.softplan.processmanagement.services.exceptions;

public class UserProcessNotFoundException extends RuntimeException {
    public UserProcessNotFoundException(String message) {
        super(message);
    }

    public UserProcessNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
