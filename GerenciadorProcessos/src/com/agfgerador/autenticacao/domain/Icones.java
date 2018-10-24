 package com.agfgerador.autenticacao.domain;

 import javax.persistence.JoinColumn;
 import javax.persistence.JoinColumns;
 import javax.persistence.Lob;
 import javax.persistence.ManyToOne;
 import javax.persistence.Column;
 import java.util.Date;
 import javax.persistence.TemporalType;
 import javax.persistence.Temporal;
 import javax.persistence.Entity;
 import javax.persistence.Table;
 import org.hibernate.annotations.Type;
 import com.agfgerador.compartilhado.domain.ObjetoPadraoSemId;
 import com.agfgerador.compartilhado.domain.ObjetoPadrao;
 
 
   @Entity 
   @Table(name = "agf_icones", schema = "agfpb_autenticacao")
   public class Icones extends ObjetoPadrao {
     private static final long serialVersionUID = 1L;
 
 
     
     @Type(type="com.agfgerador.compartilhado.util.UpperCase")
     @Column(length=255, nullable=false)
     private String nome;
     
     @Type(type="com.agfgerador.compartilhado.util.UpperCase")
     @Column(length=555, nullable=false)
     private String url;
     
public String getNome() {
       return this.nome;
     }
     
public void setNome(String nome) {
       this.nome = nome;
     }
     
public String getUrl() {
       return this.url;
     }
     
public void setUrl(String url) {
       this.url = url;
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