package br.com.sitalobr.dev.desafio.exception.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Classe responsável por adicionar filtros de interceptação de exceções e retorná-las num formato padronizado
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { HttpClientErrorException.class, HttpServerErrorException.class })
    protected ResponseEntity<Object> handleWSException(HttpStatusCodeException ex, WebRequest request) {
        ResponseEntity response = ResponseEntity.status(ex.getStatusCode()).body(ex.getStatusText());
        return handleExceptionInternal(ex, response, new HttpHeaders(), ex.getStatusCode(), request);
    }

    @ExceptionHandler(value = { IllegalArgumentException.class })
    protected ResponseEntity<Object> handleGeneralException(IllegalArgumentException ex, WebRequest request) {
        ResponseEntity response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        return handleExceptionInternal(ex, response, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
