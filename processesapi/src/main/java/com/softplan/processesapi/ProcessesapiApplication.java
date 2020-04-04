package com.softplan.processesapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class ProcessesapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProcessesapiApplication.class, args);
	}

}
