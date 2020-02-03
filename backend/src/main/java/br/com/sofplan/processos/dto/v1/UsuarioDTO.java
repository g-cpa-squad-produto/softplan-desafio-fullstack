package br.com.sofplan.processos.dto.v1;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import br.com.sofplan.processos.enums.Role;

public class UsuarioDTO {

	@Null
	private Long id;

	@NotBlank
	private String email;

	@NotBlank
	private String primeiroNome;

	@NotBlank
	private String sobrenome;

	@NotNull
	private Role perfil;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPrimeiroNome() {
		return primeiroNome;
	}

	public void setPrimeiroNome(String primeiroNome) {
		this.primeiroNome = primeiroNome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public Role getPerfil() {
		return perfil;
	}

	public void setPerfil(Role perfil) {
		this.perfil = perfil;
	}
	
}
