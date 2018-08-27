package br.com.danilopaixao.ws.core.api.security;

public interface AuthenticationService {

	AuthResponseDto authorize(final String username, final String password);

}
