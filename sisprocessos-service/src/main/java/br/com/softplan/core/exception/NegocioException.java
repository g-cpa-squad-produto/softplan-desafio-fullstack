package br.com.softplan.core.exception;

public class NegocioException extends Exception {

	private static final long serialVersionUID = 1L;

	public NegocioException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

	public NegocioException(String mensagem) {
		super(mensagem);
	}

}
