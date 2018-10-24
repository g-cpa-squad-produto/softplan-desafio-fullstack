package br.com.softplan.security.utils;

import java.util.regex.Pattern;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author emanuel
 *
 */
public class SenhaUtils {
	private static Pattern BCRYPT_PATTERN = Pattern.compile("\\A\\$2a?\\$\\d\\d\\$[./0-9A-Za-z]{53}");

	/**
	 * Gera uma nova senha
	 * 
	 * @param senha
	 * @return String
	 */

	public static String gerarBCrypt(String senha) {
		if (senha == null) {
			return senha;
		}
		BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
		return bCryptEncoder.encode(senha);
	}

	/**
	 * Verifica se a senha e valida
	 * 
	 * @param senha
	 * @param senhaEncoded
	 * @return boolean
	 */
	public static boolean senhaValida(String senha, String senhaEncoded) {
		BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
		return bCryptEncoder.matches(senha, senhaEncoded);
	}

	/**
	 * Verifica se a senha e valida
	 * 
	 * @param senha
	 * @param senhaEncoded
	 * @return boolean
	 */
	public static boolean verificaSeJaEstaCriptografa(String senha) {
		return BCRYPT_PATTERN.matcher(senha).matches();
	}

}