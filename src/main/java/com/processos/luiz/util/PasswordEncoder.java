package com.processos.luiz.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {

	public static void main(String[] args) {
		BCryptPasswordEncoder cryptPasswordEncoder = new BCryptPasswordEncoder();
		System.out.println(cryptPasswordEncoder.encode("processos"));
	}

}
