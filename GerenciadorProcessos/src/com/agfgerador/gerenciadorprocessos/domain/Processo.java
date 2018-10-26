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
   @Table(name = "processo", schema = "gerenciadorprocessos")
   public class Processo extends ObjetoPadrao {
     private static final long serialVersionUID = 1L;
 
 
     
     @ManyToOne
     @JoinColumn(name="pessoa_id",nullable=false)
     private Pessoa pessoa;
     @Column(nullable = false)
     @Temporal(TemporalType.DATE)
     private Date dtabertura;

     
     @Column(unique = true,nullable=false)
     private Integer numprocesso;
     
    public Pessoa getPessoa() {
       return pessoa;
     }
     
     public void setPessoa(Pessoa pessoa) {
       this.pessoa = pessoa;
     }
     
    public Date getDtabertura() {
       return this.dtabertura;
     }
     
     public void setDtabertura(Date dtabertura) {
       this.dtabertura = dtabertura;
     }
     
    public Integer getNumprocesso() {
       return this.numprocesso;
     }
     
     public void setNumprocesso(Integer numprocesso) {
       this.numprocesso = numprocesso;
     }
     
     public String toString(){
     return null;
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