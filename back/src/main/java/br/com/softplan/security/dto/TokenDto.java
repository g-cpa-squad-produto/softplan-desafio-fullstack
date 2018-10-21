/**
 * 
 */
package br.com.softplan.security.dto;

/**
 * @author emanuel
 *
 */
public class TokenDto {

	private String token;

	/**
	 * 
	 */
	public TokenDto() {
		// TODO Auto-generated constructor stub
	}

	public TokenDto(String token) {
		this.token = token;
		// TODO Auto-generated constructor stub
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
