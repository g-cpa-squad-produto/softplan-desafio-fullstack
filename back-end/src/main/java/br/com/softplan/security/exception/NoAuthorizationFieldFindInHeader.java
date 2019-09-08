package br.com.softplan.security.exception;

public class NoAuthorizationFieldFindInHeader extends RuntimeException {

    public NoAuthorizationFieldFindInHeader(){
        super("Campo Authorization não está presente no header da requisição");
    }
}
