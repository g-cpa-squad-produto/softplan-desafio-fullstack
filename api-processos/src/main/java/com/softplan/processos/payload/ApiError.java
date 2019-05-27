package com.softplan.processos.payload;

import java.util.Set;

public class ApiError {

    private final String message;
    private final Set<String> errors;

    public ApiError(final String message) {
        this.message = message;
        this.errors = null;
    }

    public ApiError(final Set<String> errors) {
        this.message = null;
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public Set<String> getErrors() {
        return errors;
    }

}
