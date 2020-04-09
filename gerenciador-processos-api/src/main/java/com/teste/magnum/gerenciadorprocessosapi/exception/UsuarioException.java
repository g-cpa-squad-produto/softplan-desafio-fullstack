package com.teste.magnum.gerenciadorprocessosapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class UsuarioException extends Exception {

    public UsuarioException(String message) {
        super(message);
    }
}
