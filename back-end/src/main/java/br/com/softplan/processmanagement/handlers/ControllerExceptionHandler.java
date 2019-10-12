package br.com.softplan.processmanagement.handlers;

import br.com.softplan.processmanagement.domain.ErrorDetail;
import br.com.softplan.processmanagement.services.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDetail> handleUserNotFoundException(UserNotFoundException e, HttpServletRequest request) {
        ErrorDetail error = new ErrorDetail(e.getMessage(), 404l, System.currentTimeMillis(), "");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(ProcessNotFoundException.class)
    public ResponseEntity<ErrorDetail> handleProcessNotFoundException(ProcessNotFoundException e, HttpServletRequest request) {
        ErrorDetail error = new ErrorDetail(e.getMessage(), 404l, System.currentTimeMillis(), "");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(EmailAlreadyUsedException.class)
    public ResponseEntity<ErrorDetail> handleEmailAlreadyUsedException(EmailAlreadyUsedException e, HttpServletRequest request){
        ErrorDetail error = new ErrorDetail(e.getMessage(), 409l, System.currentTimeMillis(), "");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(UserProcessNotFoundException.class)
    public ResponseEntity<ErrorDetail> handleUserProcessNotFoundException(UserProcessNotFoundException e, HttpServletRequest request){
        ErrorDetail error = new ErrorDetail(e.getMessage(), 404l, System.currentTimeMillis(), "");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(OpinionNotFoundException.class)
    public ResponseEntity<ErrorDetail> handleOpinionNotFoundException(OpinionNotFoundException e, HttpServletRequest request){
        ErrorDetail error = new ErrorDetail(e.getMessage(), 404l, System.currentTimeMillis(), "");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

}
