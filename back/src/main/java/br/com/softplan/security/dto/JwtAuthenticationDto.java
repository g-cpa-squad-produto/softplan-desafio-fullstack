/**
 * 
 */
package br.com.softplan.security.dto;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author emanuel
 *
 */
public class JwtAuthenticationDto {

	@NotEmpty(message = "Login não pode ser vazio")
	private String login;
	@NotEmpty(message = "Senha não pode ser vazia")
	private String senha;

	/**
	 * 
	 */
	public JwtAuthenticationDto() {
		// TODO Auto-generated constructor stub
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
