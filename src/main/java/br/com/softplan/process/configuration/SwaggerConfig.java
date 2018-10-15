package br.com.softplan.process.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket apiConfigDocs() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.softplan.process.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo infoDocs() {
        return new ApiInfo(
            "Desafio - Gerenciador de processos",
            "Documetnação baseada na aplicação de gerenciamento de processos.",
            "1.0",
            "Terms",
            new Contact("Henry", "" , "henrysjfarias@gmail.com"),
            "Apache License",
            "Url",
            new ArrayList<>()
        );
    }
}
