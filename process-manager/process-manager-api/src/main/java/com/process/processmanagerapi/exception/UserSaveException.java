package com.process.processmanagerapi.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserSaveException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UserSaveException(final String message) {
        super(message);
    }
}

