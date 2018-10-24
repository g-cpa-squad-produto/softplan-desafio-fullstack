package br.com.softplan.processos.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsFilterConfiguration {

    // Cria o filtro com as configurações do cors
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

	// Cria as configurações do cors
	CorsConfiguration config = new CorsConfiguration();
	config.setAllowCredentials(true);
	// Permite que qualquer origem consiga acessar a API
	config.addAllowedOrigin("*");
	config.addAllowedHeader("*");
	config.addAllowedMethod("*");

	// Adiciona a configuração a todas as urls
	source.registerCorsConfiguration("/**", config);

	FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
	bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
	return bean;
    }
}
