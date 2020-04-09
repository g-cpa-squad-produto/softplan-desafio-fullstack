package com.teste.magnum.gerenciadorprocessosapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ProcessoException extends Exception {

    public ProcessoException(String message) {
        super(message);
    }
}
