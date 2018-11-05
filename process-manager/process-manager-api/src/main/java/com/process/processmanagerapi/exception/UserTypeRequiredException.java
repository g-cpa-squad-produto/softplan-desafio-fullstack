package com.process.processmanagerapi.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserTypeRequiredException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UserTypeRequiredException(final String message) {
        super(message);
    }
}

