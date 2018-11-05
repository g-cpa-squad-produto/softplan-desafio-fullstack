package com.process.processmanagerapi.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserNotAuthorizedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UserNotAuthorizedException(final String message) {
        super(message);
    }
}

