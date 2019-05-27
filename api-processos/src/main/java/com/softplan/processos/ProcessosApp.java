package com.softplan.processos;

import com.softplan.processos.configuration.JacksonConfiguration;
import com.softplan.processos.configuration.WebConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@ImportAutoConfiguration({JacksonConfiguration.class, WebConfiguration.class})
public class ProcessosApp {

    public static void main(String[] args) {
        SpringApplication.run(ProcessosApp.class, args);
    }

}
