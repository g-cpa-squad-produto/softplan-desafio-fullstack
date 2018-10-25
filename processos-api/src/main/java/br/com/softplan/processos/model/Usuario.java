package br.com.softplan.processos.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Usuario implements Serializable {

    private static final long serialVersionUID = 3464739211255422784L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codigo;

    @Email
    @NotEmpty
    @Size(min = 1, max = 100)
    @Column(updatable = false, nullable = false)
    private String email;

    @Size(min = 3, max = 50)
    @NotEmpty
    private String nome;

    @Size(min = 3, max = 500)
    @NotEmpty
    private String senha;

    @ManyToMany
    @JoinTable(name = "usuario_permissao", joinColumns = @JoinColumn(name = "codigo_usuario"), inverseJoinColumns = @JoinColumn(name = "permissao"))
    @NotEmpty
    private Set<Permissao> permissoes;

    public Long getCodigo() {
	return codigo;
    }

    public void setCodigo(Long codigo) {
	this.codigo = codigo;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
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
	this.senha = senha;
    }

    public Set<Permissao> getPermissoes() {
	return permissoes;
    }

    public void setPermissoes(Set<Permissao> permissoes) {
	this.permissoes = permissoes;
    }
}
