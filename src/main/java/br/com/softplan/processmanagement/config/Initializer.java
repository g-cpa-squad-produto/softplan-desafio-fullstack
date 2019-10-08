package br.com.softplan.processmanagement.config;

import br.com.softplan.processmanagement.domain.User;
import br.com.softplan.processmanagement.domain.UserType;
import br.com.softplan.processmanagement.repositories.UsersRepository;
import br.com.softplan.processmanagement.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Initializer implements ApplicationRunner {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
//    private PasswordEncoder bcryptEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        User user = new User();
        user.setName("Administrador");
        user.setType(UserType.ADMIN);
//        user.setPassword(bcryptEncoder.encode("12345"));
        user.setEmail("admin@email.com");
        usersRepository.save(user);

        user = new User();
        user.setName("Triador");
        user.setType(UserType.TRIADOR);
//        user.setPassword(bcryptEncoder.encode("12345"));
        user.setEmail("triador@email.com");
        usersRepository.save(user);

        user = new User();
        user.setName("Finalizador");
        user.setType(UserType.FINALIZADOR);
//        user.setPassword(bcryptEncoder.encode("12345"));
        user.setEmail("finalizador@email.com");
        usersRepository.save(user);

        usersRepository.findAll().forEach(System.out::println);
    }
}
