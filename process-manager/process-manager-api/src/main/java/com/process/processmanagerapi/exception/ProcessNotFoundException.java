package com.process.processmanagerapi.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ProcessNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ProcessNotFoundException(final String message) {
        super(message);
    }
}

