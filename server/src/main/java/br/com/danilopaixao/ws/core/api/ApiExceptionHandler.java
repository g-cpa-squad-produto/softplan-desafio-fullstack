package br.com.danilopaixao.ws.core.api;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.damilopaixao.ws.core.ResourceNotFoundException;
import br.com.danilopaixao.ws.core.http.HttpRestClientException;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
 
	@ExceptionHandler(HttpRestClientException.class)
    protected ResponseEntity<ApiErrorResponse[]> httpRestClientError(HttpRestClientException ex) {
    	return ResponseEntity.status(ex.getError().getStatus()).body(new ApiErrorResponse[] { ex.getError() });
    }
	

	
	@ExceptionHandler( {  
		ResourceNotFoundException.class
	} )
	protected ResponseEntity<ApiErrorResponse[]> notFoundError(final Exception ex, final HttpServletRequest request) {
		return handleError(ex, HttpStatus.NOT_FOUND, ApiErrorCode.GLOBAL);
	}
	
	@ExceptionHandler( {  
		NullPointerException.class
	} )
	protected ResponseEntity<ApiErrorResponse[]> badRequestError(final Exception ex, final HttpServletRequest request) {
		return handleError(ex, HttpStatus.BAD_REQUEST, ApiErrorCode.VALIDATION);
	}
    
	private ResponseEntity<ApiErrorResponse[]> handleError(Throwable exception, HttpStatus httpStatus, ApiErrorCode errorCode) {
		final String message = Optional.ofNullable(exception)
				.map(e -> e.getMessage())
				.filter(StringUtils::isNotBlank)
				.orElse(exception.getClass().getSimpleName());

		final ApiErrorResponse error = ApiErrorResponse.of(message, errorCode, httpStatus);
		return ResponseEntity.status(error.getStatus()).body(new ApiErrorResponse[] { error });
	}

}