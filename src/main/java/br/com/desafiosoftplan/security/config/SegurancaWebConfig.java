package br.com.desafiosoftplan.security.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import br.com.desafiosoftplan.security.UsuarioDetailsService;

/**
 * Configuração de segurança
 * @author Setembro/2018: Jessica Etiene Marques Almeida
 */

@EnableWebSecurity  //Anotação para habilitar os recursos de segurança na aplicação
public class SegurancaWebConfig  extends WebSecurityConfigurerAdapter
{
   @Autowired
   private UsuarioDetailsService usuarioDetailsService;


   @Override
   protected void configure(HttpSecurity http) throws Exception {
      http
         .authorizeRequests()
         .antMatchers("/resources/**", "/webjars/**").permitAll()
         .antMatchers("/usuario/**").hasRole("ADMINISTRADOR")
         .antMatchers("/", "/add", "/edit/{id}","/save").hasRole("USUARIO_TRIADOR")
         .antMatchers("/listarprocessos").hasRole("USUARIO_FINALIZADOR")
         .anyRequest().authenticated()
         .and()
         .formLogin()
         .loginPage("/login")
         .permitAll();

   }

   @Override
   protected void configure(AuthenticationManagerBuilder builder) throws Exception
   {
      builder
         .userDetailsService(usuarioDetailsService)
         .passwordEncoder(new BCryptPasswordEncoder());
   }


}
