package br.com.danilopaixao.ws.core.api;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;

public class ApiErrorResponse {

    private final HttpStatus status;

    private final ApiErrorCode code;

    private final String message;

    private final String timestamp;

    protected ApiErrorResponse(final String message, final ApiErrorCode code, final HttpStatus status) {
        this.message = message;
        this.code = code;
        this.status = status;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
    }

    public static ApiErrorResponse of(final String message, final ApiErrorCode errorCode, final HttpStatus status) {
        return new ApiErrorResponse(message, errorCode, status);
    }
    
    public ApiErrorCode getCode() {
		return code;
	}
    
    public String getMessage() {
		return message;
	}
    
    public HttpStatus getStatus() {
		return status;
	}
    
    public String getTimestamp() {
		return timestamp;
	}

}