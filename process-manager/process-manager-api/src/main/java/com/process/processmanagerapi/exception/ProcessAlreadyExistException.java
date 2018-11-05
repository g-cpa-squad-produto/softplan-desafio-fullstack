package com.process.processmanagerapi.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ProcessAlreadyExistException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ProcessAlreadyExistException(final String message) {
        super(message);
    }
}

