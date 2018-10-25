package br.com.softplan.usuario.dto;

import br.com.softplan.security.enums.PerfilEnum;
import br.com.softplan.usuario.modelos.Usuario;

/**
 * @author emanuel
 *
 */
public class UsuarioDto {

	public Integer id;
	public String login;
	public String email;
	public String telefone;
	public PerfilEnum perfil;
	public Boolean ativo;
	public String nome;

	public UsuarioDto(Usuario usuario) {
		this.id = usuario.getId();
		this.login = usuario.getLogin();
		this.email = usuario.getEmail();
		this.telefone = usuario.getTelefone();
		this.perfil = usuario.getPerfil();
		this.ativo = usuario.getAtivo();
		this.nome = usuario.getNome();
	}
}
