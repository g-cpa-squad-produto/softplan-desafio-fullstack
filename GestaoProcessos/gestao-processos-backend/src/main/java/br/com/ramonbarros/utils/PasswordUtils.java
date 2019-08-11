package br.com.ramonbarros.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public abstract class PasswordUtils {

	private static final BCryptPasswordEncoder ENCODER = new BCryptPasswordEncoder(16);

	private PasswordUtils() {}
	
	public static String crypt(String password) {
		return ENCODER.encode( password );
	}


	public static boolean verify(String password, String hashedPassword) {
		return ENCODER.matches( password, hashedPassword );
	}
}