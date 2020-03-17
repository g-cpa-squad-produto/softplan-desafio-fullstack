package br.com.luizgustavo.processevaluation.controller.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String userMessage = "Requisição contém atributos inválidos.";
		String devMessage = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
		List<Error> errors = Arrays.asList(new Error(userMessage, devMessage));
		return handleExceptionInternal(ex, errors, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<Error> errors = generateErrorsList(ex.getBindingResult());
		return handleExceptionInternal(ex, errors, headers, status, request);
	}

	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex,
			WebRequest request) {
		String userMessage = "Recurso não encontrado";
		String devMessage = ex.toString();
		List<Error> errors = Arrays.asList(new Error(userMessage, devMessage));
		return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex,
			WebRequest request) {
		String userMessage = "Operação não permitida";
		String devMessage = ExceptionUtils.getRootCauseMessage(ex);
		List<Error> errors = Arrays.asList(new Error(userMessage, devMessage));
		return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler(ExpiredTokenException.class)
	public ResponseEntity<Object> handleTokenExpiredTokenException(ExpiredTokenException ex, WebRequest request) {
		String userMessage = "Token do usuário expirou";
		String devMessage = ex.toString();
		List<Error> errors = Arrays.asList(new Error(userMessage, devMessage));
		return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
	}
	
	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<Object> handleUserNameNotFoundException(UsernameNotFoundException ex, WebRequest request) {
		String userMessage = "Usuário e/ou senha inválido(s)";
		String devMessage = ex.toString();
		List<Error> errors = Arrays.asList(new Error(userMessage, devMessage));
		return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	private List<Error> generateErrorsList(BindingResult bindingResult) {
		List<Error> errors = new ArrayList<>();

		for (FieldError error : bindingResult.getFieldErrors()) {
			String userMessage = error.getField();
			String devMessage = error.toString();
			errors.add(new Error(userMessage, devMessage));
		}

		return errors;
	}

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Error {
		
		private String userMessage;
		private String devMessage;
	}

}
