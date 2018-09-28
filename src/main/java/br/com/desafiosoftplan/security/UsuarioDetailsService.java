package br.com.desafiosoftplan.security;


import java.util.ArrayList;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import br.com.desafiosoftplan.model.Usuario;
import br.com.desafiosoftplan.service.UsuarioService;

/**
 * Credenciais do usuáirio do sistema
 * @author Setembro/2018: Jessica Etiene Marques Almeida
 */
@Component
public class UsuarioDetailsService implements UserDetailsService
{
   @Autowired
   private UsuarioService usuarioService;

   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
   {
      Usuario usuario = usuarioService.buscaUsuarioPorLogin(username);
      if (usuario == null) {
         throw new UsernameNotFoundException("Usuário não encontrado!");
      }

      return new UsuarioSistema(usuario.getId(), usuario.getNome(), usuario.getLogin(), usuario.getSenha(), authorities(usuario));
   }

   public Collection<? extends GrantedAuthority> authorities(Usuario usuario) {
      Collection<GrantedAuthority> auths = new ArrayList<>();

      auths.add(new SimpleGrantedAuthority("ROLE_" + usuario.getTipoUsuario()));

      return auths;
   }



}
