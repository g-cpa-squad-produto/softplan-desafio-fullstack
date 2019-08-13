package com.renantabaresmachado.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.renantabaresmachado.security.UserSSecurity;

public class UserService {
	public static UserSSecurity authenticated() {
		try {
			return (UserSSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}catch (Exception e) {
			return null;
		}
	}

}
