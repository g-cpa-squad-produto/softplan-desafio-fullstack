package br.com.luizgustavo.processevaluation.security.util;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.luizgustavo.processevaluation.controller.exception.ExpiredTokenException;
import br.com.luizgustavo.processevaluation.model.User;

public class AuthenticatedUser {

	public static User get() {
		try {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user;
		} catch(Exception ex) {
			throw new ExpiredTokenException();
		}
	}
}
