package br.com.sofplan.processos.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.sofplan.processos.enums.Role;

@Entity
@Table(name = "tb_usuario")
public class Usuario implements Serializable {

	private static final long serialVersionUID = -3989934594054062646L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @Column(length = 100, unique = true, nullable = false)
    private String email;
    
    @Column(length = 100, nullable = false)
    private String primeiroNome;

    @Column(length = 100, nullable = false)
    private String sobrenome;
    
    @Column(length = 50)
    @Enumerated(EnumType.STRING)
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
