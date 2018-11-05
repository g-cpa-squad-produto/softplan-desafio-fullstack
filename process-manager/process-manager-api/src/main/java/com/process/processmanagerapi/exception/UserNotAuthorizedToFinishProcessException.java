package com.process.processmanagerapi.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserNotAuthorizedToFinishProcessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UserNotAuthorizedToFinishProcessException(final String message) {
        super(message);
    }
}

