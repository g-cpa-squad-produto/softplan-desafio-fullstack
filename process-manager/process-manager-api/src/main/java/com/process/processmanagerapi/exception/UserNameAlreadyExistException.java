package com.process.processmanagerapi.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserNameAlreadyExistException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UserNameAlreadyExistException(final String message) {
        super(message);
    }
}

