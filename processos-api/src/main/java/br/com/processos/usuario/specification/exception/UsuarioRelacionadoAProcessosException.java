package br.com.processos.usuario.specification.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class UsuarioRelacionadoAProcessosException extends RuntimeException {
    public UsuarioRelacionadoAProcessosException() {
        super(UsuarioRelacionadoAProcessosException.class.getName());
    }
}
