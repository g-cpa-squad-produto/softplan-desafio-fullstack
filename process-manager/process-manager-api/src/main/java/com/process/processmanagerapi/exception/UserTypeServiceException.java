package com.process.processmanagerapi.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserTypeServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UserTypeServiceException(final String message) {
        super(message);
    }
}

