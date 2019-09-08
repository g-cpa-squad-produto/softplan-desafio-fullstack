package br.com.softplan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories(basePackages = {"br.com.softplan.**.repository"})
public class SoftplanDesafioFullstackBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoftplanDesafioFullstackBackendApplication.class, args);
	}

}
