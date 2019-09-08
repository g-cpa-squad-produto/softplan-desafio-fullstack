package br.com.softplan.processo.exception;

public class ProcessoNaoExistenteException extends RuntimeException {

    public ProcessoNaoExistenteException(){
        super("Processo não existe");
    }
}
