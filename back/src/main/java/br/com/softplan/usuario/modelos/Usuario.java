package br.com.softplan.usuario.modelos;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.softplan.security.enums.PerfilEnum;

/**
 * @author emanuel
 *
 */
@Entity(name = "Usuario")
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	@Column
	private String nome;

	@Column
	private String email;

	@NotNull
	@Column
	private String login;

	@Column
	private String senha;

	@Column
	private String telefone;

	@Column(name = "data_hora")
	private Timestamp dataHora = new Timestamp(new Date().getTime());

	@Column(name = "data_hora_ultima_modificacao")
	private Timestamp dataHoraUlimaModificacao;

	@Column
	private String token;

	@Column
	private Boolean ativo = Boolean.TRUE;

	@Column
	private Boolean deletado = Boolean.FALSE;

	@Column
	@Enumerated
	private PerfilEnum perfil;

	public Usuario() {
	}

	public Usuario(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Timestamp getDataHora() {
		return dataHora;
	}

	public void setDataHora(Timestamp dataHora) {
		this.dataHora = dataHora;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public PerfilEnum getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilEnum perfil) {
		this.perfil = perfil;
	}

	public Timestamp getDataHoraUlimaModificacao() {
		return dataHoraUlimaModificacao;
	}

	public void setDataHoraUlimaModificacao(Timestamp dataHoraUlimaModificacao) {
		this.dataHoraUlimaModificacao = dataHoraUlimaModificacao;
	}

	public Boolean getDeletado() {
		return deletado;
	}

	public void setDeletado(Boolean deletado) {
		this.deletado = deletado;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", email=" + email + ", login=" + login + ", senha=" + senha
				+ ", telefone=" + telefone + ", dataHora=" + dataHora + ", dataHoraUlimaModificacao="
				+ dataHoraUlimaModificacao + ", token=" + token + ", ativo=" + ativo + ", deletado=" + deletado
				+ ", perfil=" + perfil + "]";
	}

}
