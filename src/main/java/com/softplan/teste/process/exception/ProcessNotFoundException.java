package com.softplan.teste.process.exception;

public class ProcessNotFoundException extends Exception{
    public ProcessNotFoundException(String message) {
        super(message);
    }

    public ProcessNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
