package br.com.danilopaixao.ws.core.api.security;

import org.springframework.security.core.AuthenticationException;

public final class UsernamePasswordAuthenticationException extends AuthenticationException {

	private static final long serialVersionUID = -1453416027905539627L;

	public UsernamePasswordAuthenticationException() {
		super("Username or Password is invalid.");
	}
}
