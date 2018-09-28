package br.com.desafiosoftplan.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Classe Representativa da entidade Processo
 * @author Setembro/2018: Jessica Etiene Marques Almeida
 */
@Entity
public class Processo implements Serializable
{
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   @Column(name = "descricao")
   private String descricao;

   @Temporal(TemporalType.DATE)
   @DateTimeFormat(pattern = "yyyy-MM-dd")
   @Column(name = "data_processo")
   private Date data;

   @OneToMany(mappedBy = "processo", targetEntity = Parecer.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   private List<Parecer> parecers;

   public Long getId()
   {
      return id;
   }

   public void setId(Long id)
   {
      this.id = id;
   }


   public String getDescricao()
   {
      return descricao;
   }

   public void setDescricao(String descricao)
   {
      this.descricao = descricao;
   }


   public Date getData()
   {
      return data;
   }

   public void setData(Date data)
   {
      this.data = data;
   }


   public List<Parecer> getParecers()
   {
      return parecers;
   }

   public void setParecers(List<Parecer> parecers)
   {
      this.parecers = parecers;
   }
}
