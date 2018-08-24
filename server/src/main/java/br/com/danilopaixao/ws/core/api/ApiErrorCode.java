package br.com.danilopaixao.ws.core.api;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ApiErrorCode {
	SERVER(1),
    GLOBAL(2),
	VALIDATION(3),
	AUTHENTICATION(4),
	JWT_TOKEN_EXPIRED(5),
	BPM(6),
	AUTH(8);
    
    private Integer errorCode;

    private ApiErrorCode(final Integer errorCode) {
        this.errorCode = errorCode;
    }

    @JsonValue
    public Integer getErrorCode() {
        return errorCode;
    }
}