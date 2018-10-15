package br.com.softplan.process.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler(value = ApplicationException.class)
    public ResponseEntity<ErrorInfo> applicationErrorHandler(HttpServletRequest req, ApplicationException e) {
        String message = getMessage(e);
        ErrorInfo errorInfo = new ErrorInfo(message);

        log.error(message, e);

        return createErrorInfoResponseEntity(errorInfo);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorInfo> defaultErrorHandler(HttpServletRequest req, Exception e) {
        String message = getMessage(e);
        ErrorInfo errorInfo = new ErrorInfo(message);

        log.error(message, e);

        return createErrorInfoResponseEntity(errorInfo);
    }

    private String getMessage(Exception e) {
        String message = e.getMessage();

        if (message.isEmpty()) {
            message = "Ocorreu um erro ao executar a requisição";
        }

        return message;
    }

    private ResponseEntity<ErrorInfo> createErrorInfoResponseEntity(ErrorInfo errorInfo) {
        HttpStatus status;
        status = HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(errorInfo, status);
    }
}
