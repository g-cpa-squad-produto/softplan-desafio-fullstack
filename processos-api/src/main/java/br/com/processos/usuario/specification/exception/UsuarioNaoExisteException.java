package br.com.processos.usuario.specification.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class UsuarioNaoExisteException extends RuntimeException {
    public UsuarioNaoExisteException() {
        super(UsuarioNaoExisteException.class.getName());
    }
}
