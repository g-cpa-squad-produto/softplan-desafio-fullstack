package br.com.softplan.processo.exception;

public class ProcessoExistenteException extends RuntimeException {

    public ProcessoExistenteException(){
        super("Número de processo já existe");
    }
}
