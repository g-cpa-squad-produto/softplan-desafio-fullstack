package com.process.processmanagerapi.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserNotAuthorizedToIncludeProcessOpinionException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UserNotAuthorizedToIncludeProcessOpinionException(final String message) {
        super(message);
    }
}

