package br.com.softplan.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import br.com.softplan.security.JWTAuthenticationFilter;
import br.com.softplan.security.JwtAuthenticationEntryPoint;

/**
 * @author emanuel
 *
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtAuthenticationEntryPoint unauthorizedHandler;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public JWTAuthenticationFilter authenticationTokenFilterBean() {
		return new JWTAuthenticationFilter();
	}

	protected void configure(HttpSecurity httpSecurity) throws Exception {
		// Desabilitando o cors para a API e Adicionando exception de nao autorizado
		httpSecurity.csrf().disable().exceptionHandling().authenticationEntryPoint(unauthorizedHandler);

		// Configurando o protocolo de http STATELESS
		httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		// Adicionando livre acesso para apis publicas
		httpSecurity.authorizeRequests().antMatchers("/").permitAll();
		httpSecurity.authorizeRequests().antMatchers("/api-publica/**").permitAll().anyRequest().authenticated();

		httpSecurity.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
		httpSecurity.headers().cacheControl();
	}
}
