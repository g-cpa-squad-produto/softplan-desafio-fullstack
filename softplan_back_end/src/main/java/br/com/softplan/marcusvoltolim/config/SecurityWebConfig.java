package br.com.softplan.marcusvoltolim.config;

import br.com.softplan.marcusvoltolim.auth.UserDetailsJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityWebConfig extends WebSecurityConfigurerAdapter {
	
	private final UserDetailsJpaService userDetailsService;
	
	@Autowired
	public SecurityWebConfig(UserDetailsService userDetailsService) {
		this.userDetailsService = (UserDetailsJpaService) userDetailsService;
	}
	
	@Bean
	@Override
	protected UserDetailsService userDetailsService() {
		return userDetailsService;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder builder) throws Exception {
		builder
				.userDetailsService(userDetailsService)
				.passwordEncoder(new BCryptPasswordEncoder());
	}
	
	
	@Override
	protected void configure(HttpSecurity http)
			throws Exception {
		http.csrf().disable().formLogin().and().rememberMe().and()
				.authorizeRequests()
				.antMatchers("/login").permitAll()
				.antMatchers("/authorization").permitAll()
				.anyRequest().permitAll();
		//				.anyRequest().authenticated();
	}
	
}

