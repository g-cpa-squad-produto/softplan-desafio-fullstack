package br.com.desafiosoftplan.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Classe representativa da entidade parecer
 * @author Setembro/2018: Jessica Etiene Marques Almeida
 */
@Entity
@Table(name = "parecer")
public class Parecer implements Serializable
{
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   @OneToOne
   @JoinColumn(name="id_usuario")
   private Usuario usuario;

   @ManyToOne
   @JoinColumn(name="id_processo")
   private Processo processo;

   @Column(name = "parecer_descricao")
   private String descricao;

   @Column(name = "indicador_parecer")
   private Boolean indicadorParecer;

   public Long getId()
   {
      return id;
   }

   public void setId(Long id)
   {
      this.id = id;
   }

   public Usuario getUsuario()
   {
      return usuario;
   }

   public void setUsuario(Usuario usuario)
   {
      this.usuario = usuario;
   }

   public Processo getProcesso()
   {
      return processo;
   }

   public void setProcesso(Processo processo)
   {
      this.processo = processo;
   }

   public String getDescricao()
   {
      return descricao;
   }

   public void setDescricao(String descricao)
   {
      this.descricao = descricao;
   }

   public Boolean getIndicadorParecer()
   {
      return indicadorParecer;
   }

   public void setIndicadorParecer(Boolean indicadorParecer)
   {
      this.indicadorParecer = indicadorParecer;
   }
}
