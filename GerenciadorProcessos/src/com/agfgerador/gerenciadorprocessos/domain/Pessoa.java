 package com.agfgerador.gerenciadorprocessos.domain;

 import javax.persistence.JoinColumn;
 import javax.persistence.Lob;
 import javax.persistence.ManyToOne;
 import javax.persistence.Column;
 import java.util.Date;
 import javax.persistence.TemporalType;
 import javax.persistence.Temporal;
 import javax.persistence.Entity;
 import javax.persistence.Table;
 import org.hibernate.annotations.Type;
 import com.agfgerador.compartilhado.domain.ObjetoPadrao;

   @Entity 
   @Table(name="pessoa",schema="gerenciadorprocessos")
   public class Pessoa extends ObjetoPadrao {
     private static final long serialVersionUID = 1L;

     @ManyToOne
     @JoinColumn(name="tipopessoa_id",nullable=false)
     private Tipopessoa tipopessoa;
     
     @Lob
     @Column(nullable = true)
     private byte[] imagem;
 
     @Type(type="com.agfgerador.compartilhado.util.UpperCase")
     @Column(length=255, nullable=false)
     private String nome;
     
     @Column(nullable = true)
     @Temporal(TemporalType.DATE)
     private Date data;
 
     @Type(type="com.agfgerador.compartilhado.util.UpperCase")
     @Column(length=14, nullable=true)
     private String cpf;
     
     @Type(type="com.agfgerador.compartilhado.util.UpperCase")
     @Column(length=18, nullable=true)
     private String cnpj;
     
     @Type(type="com.agfgerador.compartilhado.util.UpperCase")
     @Column(length=9, nullable=false)
     private String cep;
     
     @Type(type="com.agfgerador.compartilhado.util.UpperCase")
     @Column(length=2, nullable=false)
     private String uf;
     
     @Type(type="com.agfgerador.compartilhado.util.UpperCase")
     @Column(length=77, nullable=false)
     private String cidade;
     
     @Type(type="com.agfgerador.compartilhado.util.UpperCase")
     @Column(length=27, nullable=false)
     private String tipologradouro;
     
     @Type(type="com.agfgerador.compartilhado.util.UpperCase")
     @Column(length=77, nullable=false)
     private String logradouro;
     
     @Type(type="com.agfgerador.compartilhado.util.UpperCase")
     @Column(length=77, nullable=false)
     private String bairro;
     
     @Type(type="com.agfgerador.compartilhado.util.UpperCase")
     @Column(length=9, nullable=false)
     private String ncasa;
     
    public Tipopessoa getTipopessoa() {
       return tipopessoa;
     }
     
     public void setTipopessoa(Tipopessoa tipopessoa) {
       this.tipopessoa = tipopessoa;
     }
     
    public byte[] getImagem() {
       return this.imagem;
     }
     
     public void setImagem(byte[] imagem) {
       this.imagem = imagem;
     }
     
    public String getNome() {
       return this.nome;
     }
     
     public void setNome(String nome) {
       this.nome = nome;
     }
     
    public Date getData() {
       return this.data;
     }
     
     public void setData(Date data) {
       this.data = data;
     }
     
    public String getCpf() {
       return this.cpf;
     }
     
     public void setCpf(String cpf) {
       this.cpf = cpf;
     }
     
    public String getCnpj() {
       return this.cnpj;
     }
     
     public void setCnpj(String cnpj) {
       this.cnpj = cnpj;
     }
     
    public String getCep() {
       return this.cep;
     }
     
     public void setCep(String cep) {
       this.cep = cep;
     }
     
    public String getUf() {
       return this.uf;
     }
     
     public void setUf(String uf) {
       this.uf = uf;
     }
     
    public String getCidade() {
       return this.cidade;
     }
     
     public void setCidade(String cidade) {
       this.cidade = cidade;
     }
     
    public String getTipologradouro() {
       return this.tipologradouro;
     }
     
     public void setTipologradouro(String tipologradouro) {
       this.tipologradouro = tipologradouro;
     }
     
    public String getLogradouro() {
       return this.logradouro;
     }
     
     public void setLogradouro(String logradouro) {
       this.logradouro = logradouro;
     }
     
     public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getNcasa() {
		return ncasa;
	}

	public void setNcasa(String ncasa) {
		this.ncasa = ncasa;
	}

	public String toString(){
     return nome;
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