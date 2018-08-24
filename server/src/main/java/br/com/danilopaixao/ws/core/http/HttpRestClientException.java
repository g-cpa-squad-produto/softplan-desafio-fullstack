package br.com.danilopaixao.ws.core.http;

import br.com.danilopaixao.ws.core.api.ApiErrorResponse;

public class HttpRestClientException extends RuntimeException {
	
	private static final long serialVersionUID = 1576835941657730713L;

	private final ApiErrorResponse error;

	public HttpRestClientException(ApiErrorResponse error) {
		super(error.getMessage());
		this.error = error;
	}

	public ApiErrorResponse getError() {
		return error;
	}
	
}
