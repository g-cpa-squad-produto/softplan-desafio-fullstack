package br.com.softplan.processos.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private final static String resourceId = "resources";

    @Override
    public void configure(HttpSecurity http) throws Exception {
	http.requestMatchers().antMatchers("/**").and().authorizeRequests().anyRequest().permitAll()
	        .antMatchers(HttpMethod.GET).permitAll().antMatchers(HttpMethod.OPTIONS).permitAll()
	        .antMatchers(HttpMethod.POST).permitAll().antMatchers(HttpMethod.PUT).permitAll()
	        .antMatchers(HttpMethod.DELETE).permitAll();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
	resources.resourceId(resourceId);
    }
}
