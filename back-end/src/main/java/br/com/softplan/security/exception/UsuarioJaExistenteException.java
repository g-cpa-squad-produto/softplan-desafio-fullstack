package br.com.softplan.security.exception;

public class UsuarioJaExistenteException extends RuntimeException {
    public UsuarioJaExistenteException(){
        super("Usuário já Existente");
    }
}
