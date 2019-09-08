package br.com.softplan.security.exception;

public class UsuarioNaoExistenteException extends RuntimeException {

    public UsuarioNaoExistenteException(){
        super("Usuário não existente");
    }
}
