package br.com.softplan.marcusvoltolim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import java.util.Locale;

@SpringBootApplication
public class MarcusVoltolimApplication extends SpringBootServletInitializer {
	
	static {
		Locale.setDefault(new Locale("pt", "BR"));
		System.setProperty("user.language", "pt");
		System.setProperty("user.region", "BR");
		System.setProperty("file.encoding", "UTF-8");
	}
	
	public static void main(String[] args) {
		SpringApplication.run(MarcusVoltolimApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(MarcusVoltolimApplication.class);
	}
	
	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(new Locale("pt", "BR"));
		return slr;
	}
	
}
