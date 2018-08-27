package br.com.danilopaixao.ws.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Getter;

@Getter
public abstract class UserException extends RuntimeException {
	
	private static final long serialVersionUID = -6634056391059593250L;

	private static final String MSG = "User Error status=%d body=%s info=%s";

	private final HttpStatus statusCode;
	private final Object error;

	public UserException(final String info, final ResponseEntity<?> response) {
		super(String.format(MSG, response.getStatusCodeValue(), response.getBody(), info));
		this.statusCode = response.getStatusCode();
		this.error = response.getBody();
	}
	
	public UserException(String message, HttpStatus statusCode, Object error) {
		this.statusCode = statusCode;
		this.error = error;
	}
}
