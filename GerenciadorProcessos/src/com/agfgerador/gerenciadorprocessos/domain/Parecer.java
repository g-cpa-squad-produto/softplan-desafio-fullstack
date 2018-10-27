 package com.agfgerador.gerenciadorprocessos.domain;

 import javax.persistence.JoinColumn;
 import javax.persistence.ManyToOne;
 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.Table;
 import org.hibernate.annotations.Type;
 import com.agfgerador.compartilhado.domain.ObjetoPadraoSemId;
import com.agfgerador.autenticacao.domain.Usuario;
 
 
 import javax.persistence.Id;
   @Entity 
   @Table(name = "parecer", schema = "gerenciadorprocessos")
   public class Parecer extends ObjetoPadraoSemId {
     private static final long serialVersionUID = 1L;
 
 
     @Id
     @ManyToOne
     @JoinColumn(name="usuario_id",nullable=false)
     private Usuario usuario;
     @Id
     @ManyToOne
     @JoinColumn(name="processo_id",nullable=false)
     private Processo processo;
     
     @Type(type="com.agfgerador.compartilhado.util.UpperCase")
     @Column(length=257, nullable=true)
     private String descricao;
     @Column(nullable = true)
 
     
    public Usuario getUsuario() {
       return usuario;
     }
     
     public void setUsuario(Usuario usuario) {
       this.usuario = usuario;
     }
     
    public Processo getProcesso() {
       return processo;
     }
     
     public void setProcesso(Processo processo) {
       this.processo = processo;
     }
     
    public String getDescricao() {
       return this.descricao;
     }
     
     public void setDescricao(String descricao) {
       this.descricao = descricao;
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