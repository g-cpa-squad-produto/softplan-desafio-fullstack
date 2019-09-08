package br.com.softplan.security.exception;

public class NoTokenInAuthorizationField extends RuntimeException {

    public NoTokenInAuthorizationField(){
        super("Token não encontrado no campo authorization do header da requisição");
    }
}
