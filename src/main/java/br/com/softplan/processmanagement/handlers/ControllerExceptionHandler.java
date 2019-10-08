package br.com.softplan.processmanagement.handlers;

import br.com.softplan.processmanagement.domain.ErrorDetail;
import br.com.softplan.processmanagement.services.exceptions.ProcessNotFoundException;
import br.com.softplan.processmanagement.services.exceptions.UserNotFoundException;
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
}
