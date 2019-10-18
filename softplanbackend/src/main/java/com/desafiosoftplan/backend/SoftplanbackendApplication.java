package com.desafiosoftplan.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableJpaAuditing
@EnableAutoConfiguration
public class SoftplanbackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoftplanbackendApplication.class, args);
//		System.out.print(new BCryptPasswordEncoder().encode("123"));
	}
	
}
