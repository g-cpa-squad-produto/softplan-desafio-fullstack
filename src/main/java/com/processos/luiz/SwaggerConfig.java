package com.processos.luiz;

import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import static springfox.documentation.builders.PathSelectors.regex;
import static com.google.common.collect.Lists.*;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.processos.luiz"))
                .paths(PathSelectors.any()) 
                .build()
                .apiInfo(metaInfo());
    }
// .paths(regex("/u.*"))
    private ApiInfo metaInfo() {

        ApiInfo apiInfo = new ApiInfo(
                "API REST",
                "Controle de processos",
                "1.0",
                "Terms of Service",
                new Contact("Luiz Jalvir da Silva", "",
                        "luizjalvir.silva@gmail.com"),
                "Apache License Version 2.0",
                "https://www.apache.org/licesen.html", new ArrayList<VendorExtension>()
        );

        return apiInfo;
    }
    private Predicate<String> multipartPaths() {
        return regex("/us.*");
    }
}
