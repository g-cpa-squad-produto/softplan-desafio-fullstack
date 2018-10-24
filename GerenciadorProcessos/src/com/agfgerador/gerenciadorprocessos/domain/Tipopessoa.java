 package com.agfgerador.gerenciadorprocessos.domain;

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
   @Table(name = "tipopessoa", schema = "gerenciadorprocessos")
   public class Tipopessoa extends ObjetoPadrao {
     private static final long serialVersionUID = 1L;
 
 
     
     @Type(type="com.agfgerador.compartilhado.util.UpperCase")
     @Column(length=77, nullable=false)
     private String descricao;
     
    public String getDescricao() {
       return this.descricao;
     }
     
     public void setDescricao(String descricao) {
       this.descricao = descricao;
     }
     
     public String toString(){
     return descricao;
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