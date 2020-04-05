package com.softplan.processesapi.application.controllers.responsestatus;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class WrongCredentialsException extends Exception{
    private static final long serialVersionUID = 1L;

    public WrongCredentialsException(String message){
        super(message);
    }
}
