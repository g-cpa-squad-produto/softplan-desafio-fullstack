package br.com.processo.prjdemo.model;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 
 * @author Guilherme Sena
 * Classe que mapeia o USUARIO
 */
@Entity
public class Usuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//ATRIBUTOS

	@Id
	@SequenceGenerator(name="usu_generator",sequenceName="usu_seq",allocationSize=10)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usu_generator")
	private Long id;
	
	@Column
	@NotBlank
	@NotNull
	private String login;
	
	@Column
	@NotBlank
	@NotNull
	private String nome;
	
	@Column
	@NotBlank
	@NotNull
	private String senha;
	
	@Column
	private Date dataCriacao;
	
	@ElementCollection(targetClass = EnumPermissaoUsuario.class)
	@CollectionTable(name = "permissao_usuario", joinColumns = @JoinColumn(name = "id"))
	@Column(name = "EnumId")
	private Set<EnumPermissaoUsuario> lstPermissao= new HashSet<EnumPermissaoUsuario >();
	
	public Usuario() {
		super();
	}

	//CONSTRUTORES
	
	public Usuario(String login, String nome, String senha, Date dataCriacao, Set<EnumPermissaoUsuario> lstPermissoes) {
		super();
		this.login = login;
		this.nome = nome;
		if(senha  != null){
			this.senha = criptografaSenhaMd5(senha);//CRIPTOGRAFA SENHA
		}else {
			this.senha = senha;
		}
		
		this.dataCriacao = dataCriacao;
		this.lstPermissao = lstPermissoes;
	}
	//METODOS
	
	public String criptografaSenhaMd5(String senhaToHash) {
        String gerarSenha = null;
        if(senhaToHash != null){
	        try {
	            MessageDigest md = MessageDigest.getInstance("MD5");
	            md.update(senhaToHash.getBytes());
	            byte[] bytes = md.digest();
	            StringBuilder sb = new StringBuilder();
	            for(int i=0; i< bytes.length ;i++)
	            {
	                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	            }
	            gerarSenha = sb.toString();
	        }
	        catch (NoSuchAlgorithmException e)
	        {
	            e.printStackTrace();
	        }
        }
        return gerarSenha;
    }
	
	//GETS AND SETS

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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = criptografaSenhaMd5(senha);
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Set<EnumPermissaoUsuario> getLstPermissao() {
		return lstPermissao;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dataCriacao == null) ? 0 : dataCriacao.hashCode());
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
		if (dataCriacao == null) {
			if (other.dataCriacao != null)
				return false;
		} else if (!dataCriacao.equals(other.dataCriacao))
			return false;
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

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", login=" + login + ", dataCriacao="
				+ dataCriacao + "]";
	}
	
}

