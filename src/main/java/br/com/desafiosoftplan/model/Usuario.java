package br.com.desafiosoftplan.model;

import java.io.Serializable;
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
import javax.persistence.OneToOne;
import org.hibernate.annotations.Type;
import br.com.desafiosoftplan.dominio.TipoUsuario;

/**
 * Classe Representativa da entidade Usuario
 * @author Setembro/2018: Jessica Etiene Marques Almeida
 */
@Entity
public class Usuario implements Serializable
{
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   @Column(name = "nome")
   private String nome;

   @Column(name = "login")
   private String login;

   @Column(name = "senha")
   private String senha;

   @Type(type = "br.com.desafiosoftplan.componente.conversor.enums.TipoUsuarioConverter")
   @Column(name = "tipo_usuario")
   private TipoUsuario tipoUsuario;


   @OneToOne(mappedBy = "usuario", targetEntity = Parecer.class, fetch = FetchType.LAZY)
   private Parecer parecer;


   public Long getId()
   {
      return id;
   }

   public void setId(Long id)
   {
      this.id = id;
   }

   public String getNome()
   {
      return nome;
   }

   public void setNome(String nome)
   {
      this.nome = nome;
   }

   public String getLogin()
   {
      return login;
   }

   public void setLogin(String login)
   {
      this.login = login;
   }

   public String getSenha()
   {
      return senha;
   }

   public void setSenha(String senha)
   {
      this.senha = senha;
   }

   public TipoUsuario getTipoUsuario()
   {
      return tipoUsuario;
   }

   public void setTipoUsuario(TipoUsuario tipoUsuario)
   {
      this.tipoUsuario = tipoUsuario;
   }


   public Parecer getParecers()
   {
      return parecer;
   }

   public void setParecers(Parecer parecers)
   {
      this.parecer = parecers;
   }
}
