package br.com.ramonbarros.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.ramonbarros.enuns.PerfilEnum;

@Entity
@Table(name = "USUARIO")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static String USER_ADMIN = "admin";
	public static Long USER_ADMIN_ID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @NotBlank(message="O preenchimento do campo nome é obrigatório.")
   	@Size(message="O campo nome deve possuir no máximo 200 caracteres.", min=0, max=200)
    @Column(name = "NOME", length = 200)
    private String nome;
    
    @NotBlank(message="O preenchimento do campo login é obrigatório.")
	@Size(message="O campo login deve possuir no máximo 150 caracteres.", min=0, max=150)
    @Column(name = "LOGIN", nullable = false, unique = true, length = 150)
    private String login;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "SENHA", length = 200)
    private String senha;
    
    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String confirmaSenha;
    
    @ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="PERFIS")
	private Set<Integer> perfis = new HashSet<>();
    
	public Usuario() {
	}

	public Usuario(Long id, String nome, String login, String senha,String confirmarSenha, PerfilEnum perfil) {
		super();
		this.id = id;
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.confirmaSenha = confirmarSenha;
		this.addPerfil(perfil);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	public void setConfirmaSenha(String confirmarSenha) {
		this.confirmaSenha = confirmarSenha;
	}

	public Set<PerfilEnum> getPerfis() {
		return perfis.stream().map(x -> PerfilEnum.toEnum(x)).collect(Collectors.toSet());
	}
	
	public void addPerfil(PerfilEnum perfil) {
		perfis.add(perfil.getCod());
	}
	
	public void clearPerfis() {
		perfis.clear();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		return true;
	}

}