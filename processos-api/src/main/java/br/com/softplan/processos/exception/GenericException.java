package br.com.softplan.processos.exception;

public class GenericException extends Exception {

    private static final long serialVersionUID = -296158173832313302L;

    public GenericException() {
	super("Erro Inesperado");
    }

    public GenericException(String mensagem) {
	super(mensagem);
    }
}
