package com.process.processmanagerapi.controller;

import com.process.processmanagerapi.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ExceptionResourceHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity handleAllExceptions() {
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ProcessAlreadyExistException.class)
    public final ResponseEntity<ExceptionResponse> handleProcessAlreadyExistException(ProcessAlreadyExistException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProcessAlreadyFinishedDuringFinishProcessException.class)
    public final ResponseEntity<ExceptionResponse> handleProcessAlreadyFinishedDuringFinishProcessException(ProcessAlreadyFinishedDuringFinishProcessException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProcessAlreadyFinishedDuringIncludeProcessOpinionException.class)
    public final ResponseEntity<ExceptionResponse> handleProcessAlreadyFinishedDuringIncludeProcessOpinionException(ProcessAlreadyFinishedDuringIncludeProcessOpinionException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProcessNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleProcessNotFoundException(ProcessNotFoundException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProcessSaveException.class)
    public final ResponseEntity<ExceptionResponse> handleProcessSaveException(ProcessSaveException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNameAlreadyExistException.class)
    public final ResponseEntity<ExceptionResponse> handleUserNameAlreadyExistException(UserNameAlreadyExistException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotAuthorizedException.class)
    public final ResponseEntity<ExceptionResponse> handleUserNotAuthorizedException(UserNotAuthorizedException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotAuthorizedToIncludeProcessOpinionException.class)
    public final ResponseEntity<ExceptionResponse> handleUserNotAuthorizedToIncludeProcessOpnionException(UserNotAuthorizedToIncludeProcessOpinionException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotAuthorizedToFinishProcessException.class)
    public final ResponseEntity<ExceptionResponse> handleUserNotAuthorizedToFinishProcessException(UserNotAuthorizedToFinishProcessException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserSaveException.class)
    public final ResponseEntity<ExceptionResponse> handleUserSaveException(UserSaveException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserServiceException.class)
    public final ResponseEntity<ExceptionResponse> handleUserServiceException(UserServiceException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserTypeNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleUserTypeNotFoundException(UserTypeNotFoundException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserTypeRequiredException.class)
    public final ResponseEntity<ExceptionResponse> handleUserTypeRequiredException(UserTypeRequiredException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserTypeServiceException.class)
    public final ResponseEntity<ExceptionResponse> handleUserTypeServiceException(UserTypeServiceException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAlreadyAuthorizedToManageProcessException.class)
    public final ResponseEntity<ExceptionResponse> handleUserAlreadyAuthorizedToManageProcessException(UserAlreadyAuthorizedToManageProcessException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

}
