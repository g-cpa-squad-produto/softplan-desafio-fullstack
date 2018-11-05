package com.process.processmanagerapi.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserAlreadyAuthorizedToManageProcessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UserAlreadyAuthorizedToManageProcessException(final String message) {
        super(message);
    }
}

