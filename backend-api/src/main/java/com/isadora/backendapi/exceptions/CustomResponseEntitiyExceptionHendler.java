package com.isadora.backendapi.exceptions;

import com.isadora.backendapi.domain.Processo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomResponseEntitiyExceptionHendler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public final ResponseEntity<Object> handleUsuarioIdException(UsuarioException ex, WebRequest request){
        UsuarioExceptionResponse exceptionResponse = new UsuarioExceptionResponse(ex.getMessage());
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler
//    public final ResponseEntity<Object> handleParecerException(ParecerException ex, WebRequest request){
//        ParecerException exceptionResponse = new ParecerException(ex.getMessage());
//        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleProcessoException(ProcessoException ex, WebRequest request){
        ProcessoException exceptionResponse = new ProcessoException(ex.getMessage());
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }


}
