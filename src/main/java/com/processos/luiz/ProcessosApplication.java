package com.processos.luiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
public class ProcessosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProcessosApplication.class, args);
	}
}
