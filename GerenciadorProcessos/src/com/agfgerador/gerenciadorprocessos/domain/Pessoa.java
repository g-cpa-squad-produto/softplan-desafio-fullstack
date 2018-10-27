 package com.agfgerador.gerenciadorprocessos.domain;

 import javax.persistence.Lob;
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
     @Column(unique = true,length=14, nullable=false)
     private String cpf;
     
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