package com.process.processmanagerapi.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UserServiceException(final String message) {
        super(message);
    }
}

