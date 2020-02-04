package br.com.sofplan.processos.dto.v1;

public class CreateUsuarioDTO extends UsuarioDTO {

	private String senha;

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
