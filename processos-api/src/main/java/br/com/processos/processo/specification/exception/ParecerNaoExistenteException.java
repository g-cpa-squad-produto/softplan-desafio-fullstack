package br.com.processos.processo.specification.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class ParecerNaoExistenteException extends RuntimeException {
    public ParecerNaoExistenteException() {
        super(ParecerNaoExistenteException.class.getName());
    }
}
