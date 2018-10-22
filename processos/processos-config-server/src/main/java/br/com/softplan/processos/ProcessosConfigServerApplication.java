package br.com.softplan.processos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ProcessosConfigServerApplication {

    public static void main(String[] args) {
	SpringApplication.run(ProcessosConfigServerApplication.class, args);
    }
}
