package br.com.softplan.processos.exception;

public class RegraNegocioException extends GenericException {

    private static final long serialVersionUID = -4972555445728751048L;

    public RegraNegocioException() {
	super();
    }

    public RegraNegocioException(String mensagem) {
	super(mensagem);
    }

}
