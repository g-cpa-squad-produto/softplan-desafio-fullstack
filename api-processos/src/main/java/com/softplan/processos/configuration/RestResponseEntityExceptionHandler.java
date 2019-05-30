package com.softplan.processos.configuration;

import com.softplan.processos.payload.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<Object> entityNotFound(EntityNotFoundException exception, WebRequest request) {
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<Object> constraintViolationException(ConstraintViolationException exception, WebRequest request) {
        Set<String> messages = exception.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.toSet());
        return new ResponseEntity(new ApiError(messages), HttpStatus.BAD_REQUEST);
    }

}
