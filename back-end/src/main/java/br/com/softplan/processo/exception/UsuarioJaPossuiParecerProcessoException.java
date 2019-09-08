package br.com.softplan.processo.exception;

public class UsuarioJaPossuiParecerProcessoException extends RuntimeException {

    public UsuarioJaPossuiParecerProcessoException(){
        super("Usuário já possui cadastro para parecer no processo");
    }
}
