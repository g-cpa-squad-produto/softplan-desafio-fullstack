package com.pmanagement.pmanagementbackend;

import com.pmanagement.pmanagementbackend.domain.entity.User;
import com.pmanagement.pmanagementbackend.domain.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Application initialize
 *
 * @author Cristian Souza
 *
 * @version 1.0.0
 * @since 1.0.0, Jun 2, 2019
 */
@EnableAsync
@SpringBootApplication
@EnableAutoConfiguration
public class Application extends SpringBootServletInitializer {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * {@inheritDoc }
     *
     * @param application
     * @return
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    /**
     *
     * @param userRepository
     * @return Insere dados no banco de dados
     */
    @Bean
    public CommandLineRunner setup(UserService userRepository) {

//        logger.info("Starting initial inserts!!!");

        final User user = new User();

        user.setName("Administrator");
        user.setEmail("mail@mai.com");
        user.setUsername("admin");
        user.setPassword("password");
        user.setStatus(Boolean.TRUE);

        userRepository.saveUser(user);

//        logger.info("Finished initial inserts!!!");

        return (args) -> {
        };
    }
}
