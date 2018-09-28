package br.com.desafiosoftplan.vo;



/**
 * VO para trafego de informações de usuário
 * @author Setembro/2018: Jessica Etiene Marques Almeida
 */

public class UsuarioVO
{

   private Long id;
   private String nome;
   private String login;
   private String senha;
   private Integer tipoUsuario;

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

   public Integer getTipoUsuario()
   {
      return tipoUsuario;
   }

   public void setTipoUsuario(Integer tipoUsuario)
   {
      this.tipoUsuario = tipoUsuario;
   }
}
