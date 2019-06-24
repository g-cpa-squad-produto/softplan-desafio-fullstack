package com.challenge.api.configs;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.challenge.api.services.impl.UserServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	JwtTokenProvider jwtTokenProvider;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		UserDetailsService userDetailsService = mongoUserDetails();
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//	   http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues());
		http.cors().and().csrf().disable();
		http.httpBasic().disable().csrf().disable().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
				.antMatchers("/api/auth/login").permitAll()
				.antMatchers("/api/auth/register").permitAll()
//				.antMatchers(HttpMethod.GET,"/api/users").hasAnyAuthority("ADMIN","SUPERUSER")
//				.antMatchers(HttpMethod.DELETE,"/api/users").denyAll()
//				.antMatchers(HttpMethod.POST,"/api/users").hasAnyAuthority("ADMIN")
				.anyRequest().authenticated().and().csrf().disable()
				.exceptionHandling()
				.authenticationEntryPoint(internalErrorEntryPoint())
				.authenticationEntryPoint(unauthorizedEntryPoint()).and()
				.apply(new JwtConfigurer(jwtTokenProvider));		
	}
		
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}

	@Bean
	public PasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public AuthenticationEntryPoint unauthorizedEntryPoint() {
		return (request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
				"Unauthorized");
	}
	
	@Bean
	public AuthenticationEntryPoint internalErrorEntryPoint() {
		return (request, response, authException) -> response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
				"Internal error");
	}

	@Bean
	public UserDetailsService mongoUserDetails() {
		return new UserServiceImpl();
	}
}
