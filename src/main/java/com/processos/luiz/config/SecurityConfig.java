package com.processos.luiz.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.processos.luiz.service.CustomUserDatailService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private CustomUserDatailService customUserDatailService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.
        authorizeRequests()
        .antMatchers("/*/protected/**").hasRole("USER")
        .antMatchers("/*/admin/**").hasRole("ADMIN")
        .antMatchers("/*/triador/**").hasRole("TRIADOR")
        .antMatchers("/*/finalizador/**").hasRole("FINALIZADOR")
        .antMatchers(HttpMethod.GET, "/swagger-ui.html").permitAll()
		.and().httpBasic().and().csrf().disable();
	}
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(customUserDatailService).passwordEncoder( new BCryptPasswordEncoder());
	}
}