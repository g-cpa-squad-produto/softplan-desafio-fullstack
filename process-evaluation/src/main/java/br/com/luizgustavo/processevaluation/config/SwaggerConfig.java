package br.com.luizgustavo.processevaluation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
    	return new ApiInfoBuilder()
    			.title("Avaliação de Processos")
    			.description("API REST para manutenção no cadastro de processos e seus responsáveis.")
    			.version("1.0")
    			.contact(new Contact("Luiz Gustavo Bourscheit", "https://github.com/luizgustavoob", "oluizgustavob@gmail.com"))
    			.build();
    }
}