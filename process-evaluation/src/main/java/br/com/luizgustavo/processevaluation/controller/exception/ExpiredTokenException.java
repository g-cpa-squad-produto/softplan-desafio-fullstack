package br.com.luizgustavo.processevaluation.controller.exception;

public class ExpiredTokenException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ExpiredTokenException() {
		super("Token do usuário expirou.");
	}
}
