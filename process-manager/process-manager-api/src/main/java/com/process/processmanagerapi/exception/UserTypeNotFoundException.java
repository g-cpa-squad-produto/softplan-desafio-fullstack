package com.process.processmanagerapi.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserTypeNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UserTypeNotFoundException(final String message) {
        super(message);
    }
}

