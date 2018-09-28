package br.com.desafiosoftplan.security;


import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * Definição de usuário que será autenticado no sistema
 * @author Setembro/2018: Jessica Etiene Marques Almeida
 */
public class UsuarioSistema extends User
{
   private static final long serialVersionUID = 1L;

   private String nome;
   private Long id;


   public UsuarioSistema(Long id, String nome, String username, String password, Collection<? extends GrantedAuthority> authorities)
   {
      super(username, password, authorities);
      this.nome = nome;
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

   public Long getId()
   {
      return id;
   }

   public void setId(Long id)
   {
      this.id = id;
   }
}
