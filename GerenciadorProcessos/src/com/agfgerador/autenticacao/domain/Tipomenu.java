 package com.agfgerador.autenticacao.domain;

 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.Table;
 import org.hibernate.annotations.Type;
 import com.agfgerador.compartilhado.domain.ObjetoPadrao;
 
 
   @Entity 
   @Table(name = "agf_tipomenu", schema = "agfpb_autenticacao")
   public class Tipomenu extends ObjetoPadrao {
     private static final long serialVersionUID = 1L;
 
 
     
     @Type(type="com.agfgerador.compartilhado.util.UpperCase")
     @Column(length=133, nullable=false)
     private String nome;
     
     public String getNome() {
       return this.nome;
     }
     
     public void setNome(String nome) {
       this.nome = nome;
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