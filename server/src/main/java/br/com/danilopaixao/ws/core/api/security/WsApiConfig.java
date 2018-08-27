package br.com.danilopaixao.ws.core.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class WsApiConfig extends WebSecurityConfigurerAdapter {

	final SecurityRequestMatcher matcher = new SecurityRequestMatcher("/protected/**", "/**");

	@Autowired
	private JwtAuthenticationProvider jwtAuthenticationProvider;

	@Autowired
	private RestAuthenticationEntryPoint authenticationEntryPoint;

	@Autowired
	private RestAuthenticationFailureHandler failureHandler;

	@Autowired
	private TokenExtractor tokenExtractor;

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("OPTIONS");
		config.addAllowedMethod("GET");
		config.addAllowedMethod("POST");
		config.addAllowedMethod("PUT");
		config.addAllowedMethod("DELETE");
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	protected JwtTokenAuthenticationProcessingFilter jwtTokenAuthFilter() throws Exception {
		final JwtTokenAuthenticationProcessingFilter filter = new JwtTokenAuthenticationProcessingFilter(matcher,
				tokenExtractor, failureHandler);

		filter.setAuthenticationManager(authenticationManagerBean());

		return filter;

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(jwtAuthenticationProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http //
			.cors() //
			.and() //
			.csrf() //
			.disable() //
			.exceptionHandling() //
			.authenticationEntryPoint(this.authenticationEntryPoint) //
			.and() //
			.authorizeRequests() //
			.antMatchers("/**") //
			.permitAll() //
			.and() //
			.authorizeRequests() //
			.antMatchers("/protected/**") //
			.authenticated() //
			.and() //
			.addFilterBefore(jwtTokenAuthFilter(), UsernamePasswordAuthenticationFilter.class);
	}

}
