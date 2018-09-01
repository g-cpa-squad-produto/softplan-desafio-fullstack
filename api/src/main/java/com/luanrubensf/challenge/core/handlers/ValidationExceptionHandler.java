package com.luanrubensf.challenge.core.handlers;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

@ControllerAdvice
public class ValidationExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String VALIDATION_REASON = "VALIDATION";
    public static final String DATA_INTEGRITY_MESSAGE = "Esta operação não pode ser realizada, pois o registro possui vínculos";

    @ExceptionHandler(value = {ConstraintViolationException.class, ValidationException.class})
    protected ResponseEntity<Object> handleConstraintViolationException(RuntimeException ex, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY.value(), VALIDATION_REASON);
        return handleExceptionInternal(ex, errorMessage,
                new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY, request);
    }

    @ExceptionHandler()
    protected ResponseEntity<Object> handleAnyError(Exception ex, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
        return handleExceptionInternal(ex, errorMessage,
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    protected ResponseEntity<Object> handleHibernateConstraintViolationException(RuntimeException ex, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(DATA_INTEGRITY_MESSAGE, HttpStatus.UNPROCESSABLE_ENTITY.value(), VALIDATION_REASON);
        return handleExceptionInternal(ex, errorMessage,
                new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY, request);
    }
}
