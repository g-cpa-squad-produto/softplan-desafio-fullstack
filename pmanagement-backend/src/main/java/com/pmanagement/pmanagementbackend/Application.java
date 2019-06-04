package com.pmanagement.pmanagementbackend;

import com.pmanagement.pmanagementbackend.application.configuration.ApplicationConstants;
import com.pmanagement.pmanagementbackend.domain.entity.User;
import com.pmanagement.pmanagementbackend.domain.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

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
     * Insert the default {@link User}
     *
     * @param userService the service to process and persist the {@link User}
     * @return 
     */
    @Bean
    public CommandLineRunner setup(UserService userService) {

        logger.info("Starting initial inserts!!!");

        final User user = new User();

        user.setName("Administrator");
        user.setEmail("mail@mai.com");
        user.setUsername(ApplicationConstants.DEFAULT_USERNAME);
        user.setPassword(ApplicationConstants.DEFAULT_PASSWORD);
        user.setStatus(Boolean.TRUE);

        userService.saveUser(user);

        logger.info("Finished initial inserts!!!");
        logger.info("------------------------------");
        logger.info("Default username : " + ApplicationConstants.DEFAULT_USERNAME);
        logger.info("Default password : " + ApplicationConstants.DEFAULT_PASSWORD);
        logger.info("------------------------------");
        

        return (args) -> {
        };
    }
}
