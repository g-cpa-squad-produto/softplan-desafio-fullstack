package br.com.softplan.desafio;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;
import java.net.URL;
import java.net.URLClassLoader;

@EnableAutoConfiguration
@ComponentScan(value = { "br.com.softplan.desafio" })
@EntityScan({ "br.com.softplan.desafio.models" })
@EnableJpaRepositories({ "br.com.softplan.desafio.repository" })
@SpringBootApplication
@RestController
public class Boot {

    private static final Logger log = Logger.getLogger(Boot.class);

    public static void main(String[] args)
   {
      SpringApplication.run(Boot.class, args);
   }

    @Bean(name = "spring.datasource", destroyMethod = "shutdown")
    @Profile({"test"})
    public DataSource testDataSource() {
        log.info("TEST::::Iniciando banco memória");
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:schema.sql")
                .continueOnError(false)
                .build();
    }

    @Bean(name = "spring.datasource", destroyMethod = "shutdown")
    @Profile({"prd"})
    public DataSource dataSource() {

        log.info("PRD::::Iniciando banco memória");

        ClassLoader cl = ClassLoader.getSystemClassLoader();

        URL[] urls = ((URLClassLoader)cl).getURLs();

        for(URL url: urls){
            System.out.println(url.getFile());
        }

        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .continueOnError(false)
                .build();

//        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
//        dataSourceBuilder.type(org.apache.tomcat.jdbc.pool.DataSource.class);
//        dataSourceBuilder.driverClassName("org.h2.Driver");
//        dataSourceBuilder.url("jdbc\\:h2:~/desafio");
//        dataSourceBuilder.username("sa");
//        dataSourceBuilder.password("");
//
//        return dataSourceBuilder.build();

//        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName();
//        dataSource.setUrl();
//        dataSource.setUsername("sa");
//        dataSource.setPassword("");
//
//        Properties props = new Properties();
//        props.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
//        props.setProperty("hibernate.show_sql", "true");
//        props.setProperty("hibernate.hbm2ddl.auto", "update");
//
//        factory.setDataSource(dataSource);
//        factory.setJpaProperties(props);
//        factory.setPackagesToScan("br.com.softplan.desafio.models");

//        return dataSource;
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:3000");
            }
        };
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
    }

}
