package br.com.softplan.marcusvoltolim.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaAuditingConfiguration {
	
	private static final String ANONYMOUS = "anonymous";
	
	@Bean
	public AuditorAware<String> auditorProvider() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return () -> authentication == null ? ANONYMOUS : authentication.getName();
	}
	
}
