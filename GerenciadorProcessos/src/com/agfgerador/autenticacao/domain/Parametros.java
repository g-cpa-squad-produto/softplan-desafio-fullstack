package com.agfgerador.autenticacao.domain;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import org.hibernate.annotations.Type;

import com.agfgerador.compartilhado.domain.ObjetoPadrao;


@Entity
@Table(name="agf_parametros",schema="agfpb_autenticacao")
public class Parametros extends ObjetoPadrao {
	private static final long serialVersionUID = 1L;
	@Type(type="com.agfgerador.compartilhado.util.UpperCase")
	@Column(length=255)
	private String nome;
	@Column(unique=true,length=18)
	private String cnpj;	
	@Lob
	private byte[] brasao;
	@Type(type="com.agfgerador.compartilhado.util.UpperCase")
	@Column(length=5)
	private String acod;
	@Type(type="com.agfgerador.compartilhado.util.UpperCase")
	@Column(length=30)
	private String TipoLogradouro;	
	@Type(type="com.agfgerador.compartilhado.util.UpperCase")
	@Column(length=100)
	private String Logradouro;
	@Type(type="com.agfgerador.compartilhado.util.UpperCase")
	@Column(length=10)
	private String numero;
	@Type(type="com.agfgerador.compartilhado.util.UpperCase")
	@Column(length=100)
	private String bairro;
	@Type(type="com.agfgerador.compartilhado.util.UpperCase")
	@Column(length=100)
	private String cidade;	
	@Column(length=10)
	private String cep;
	@Type(type="com.agfgerador.compartilhado.util.UpperCase")
	@Column(length=2)
	private String uf;
	@Column(length=14)
	private String telefone;    
	
    @Type(type="com.agfgerador.compartilhado.util.UpperCase")
    @Column(length=33, nullable=false)
    private String nomereduzido;
    
    @Type(type="com.agfgerador.compartilhado.util.UpperCase")
    @Column(length=255, nullable=true)
    private String emailcontato;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public byte[] getBrasao() {
		return brasao;
	}

	public void setBrasao(byte[] brasao) {
		this.brasao = brasao;
	}

	public String getAcod() {
		return acod;
	}

	public void setAcod(String acod) {
		this.acod = acod;
	}

	public String getTipoLogradouro() {
		return TipoLogradouro;
	}

	public void setTipoLogradouro(String tipoLogradouro) {
		TipoLogradouro = tipoLogradouro;
	}

	public String getLogradouro() {
		return Logradouro;
	}

	public void setLogradouro(String logradouro) {
		Logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
    public String getNomereduzido() {
        return this.nomereduzido;
    }
      
    public void setNomereduzido(String nomereduzido) {
        this.nomereduzido = nomereduzido;
    }
	
    public String getEmailcontato() {
        return this.emailcontato;
    }
      
    public void setEmailcontato(String emailcontato) {
        this.emailcontato = emailcontato;
    }

	@Override
	public int compare(Object arg0, Object arg1) {		
		return 0;
	}

	@Override
	public String toLog() {

		return null;
	}


}

