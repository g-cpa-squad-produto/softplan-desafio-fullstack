package com.luanrubensf.challenge.core.validation;

public abstract class AbstractValidation<T> {

    private String message;

    public AbstractValidation() {
    }

    public AbstractValidation(String message) {
        this.message = message;
    }

    public abstract boolean validateEntity(T candidate);

    public ValidationConstraint validate(T candidate) {
        boolean isValid = validateEntity(candidate);
        return new ValidationConstraint(message, isValid);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
