package com.desafiosoftplan.backend.security;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	ImplementsUserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable().authorizeRequests()
			.antMatchers("/**").permitAll()
			.antMatchers(HttpMethod.POST, "/login").permitAll()
			.antMatchers(HttpMethod.GET, "/processo").hasRole("ADMIN")
			.antMatchers(HttpMethod.POST, "/processo").hasRole("ADMIN")
			.antMatchers(HttpMethod.PUT, "/processo").access("hasRole('ADMIN') or hasRole('TRIADOR') or hasRole('FINALIZADOR')")
			.antMatchers(HttpMethod.DELETE, "/processo").hasRole("ADMIN")
			.anyRequest().authenticated()
			.and()
			.addFilterBefore(new LoginFilter("/login", this.authenticationManager()),UsernamePasswordAuthenticationFilter.class)
			.addFilterBefore(new AuthenticationFilter(),UsernamePasswordAuthenticationFilter.class)
			.cors().configurationSource(new CorsConfigurationSource() {
				
				 @Override
			        public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
			            CorsConfiguration config = new CorsConfiguration();
			            config.setAllowedHeaders(Collections.singletonList("*"));
			            config.setAllowedMethods(Collections.singletonList("*"));
			            config.setAllowedOrigins(Collections.singletonList("*"));
			            config.addAllowedOrigin("*");
			            config.setAllowCredentials(true);
			            return config;
			        }
				}).and()
			.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
		
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
			.passwordEncoder(new BCryptPasswordEncoder());
	}
/*		httpSecurity
//			.cors().configurationSource(new CorsConfigurationSource() {
//			
//			 @Override
//		        public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
//		            CorsConfiguration config = new CorsConfiguration();
//		            config.setAllowedHeaders(Collections.singletonList("*"));
//		            config.setAllowedMethods(Collections.singletonList("*"));
//		            config.setAllowedOrigins(Collections.singletonList("*"));
//		            config.addAllowedOrigin("*");
//		            config.setAllowCredentials(true);
//		            return config;
//		        }
//			}).and()
			.csrf().and().authorizeRequests()
			.antMatchers("/**").permitAll()
			.antMatchers(HttpMethod.POST, "/login").permitAll()
			.antMatchers(HttpMethod.GET, "/processo").hasRole("ADMIN")
			.antMatchers(HttpMethod.POST, "/processo").hasRole("ADMIN")
			.antMatchers(HttpMethod.PUT, "/processo").access("hasRole('ADMIN') or hasRole('TRIADOR') or hasRole('FINALIZADOR')")
			.antMatchers(HttpMethod.DELETE, "/processo").hasRole("ADMIN")
			.anyRequest().authenticated()
			.and()
			.addFilterBefore(new LoginFilter("/login", this.authenticationManager()),UsernamePasswordAuthenticationFilter.class)
			.addFilterBefore(new AuthenticationFilter(),UsernamePasswordAuthenticationFilter.class)
			.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
		
	}*/	
}