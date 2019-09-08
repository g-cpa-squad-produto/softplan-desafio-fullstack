package br.com.softplan.processo.exception;

public class ParecerProcessoNaoExistenteException extends RuntimeException {

    public ParecerProcessoNaoExistenteException(){
        super("Parecer processo não existe");
    }
}
