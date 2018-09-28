package br.com.desafiosoftplan.vo;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import br.com.desafiosoftplan.model.Parecer;
import br.com.desafiosoftplan.model.Processo;
import br.com.desafiosoftplan.model.Usuario;

/**
 * VO para trafego de informções de processo e usuário
 * @author Setembro/2018: Jessica Etiene Marques Almeida
 */
public class ProcessoVO
{
   private Processo processo;
   private Usuario usuario;


   public Processo getProcesso()
   {
      return processo;
   }

   public void setProcesso(Processo processo)
   {
      this.processo = processo;
   }

   public Usuario getUsuario()
   {
      return usuario;
   }

   public void setUsuario(Usuario usuario)
   {
      this.usuario = usuario;
   }
}
