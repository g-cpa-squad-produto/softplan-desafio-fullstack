package br.com.fwom.api;

import br.com.fwom.api.models.Role;
import br.com.fwom.api.models.RoleEnum;
import br.com.fwom.api.models.User;
import br.com.fwom.api.repositories.RoleRepository;
import br.com.fwom.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableJpaAuditing
public class Application implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        /* Check if is the first time that is running */
        if (userRepository.findAll().size() == 0) {
            userRepository.deleteAllInBatch();
            roleRepository.deleteAllInBatch();
            /* Create default role*/
            List<Role> roles = new ArrayList<>();
            roles.add(new Role(RoleEnum.ADMIN.getValue(), "ROLE_ADMIN"));
            roles.add(new Role(RoleEnum.TRIAL.getValue(), "ROLE_TRIAL"));
            roles.add(new Role(RoleEnum.EDITOR.getValue(), "ROLE_EDITOR"));
            roleRepository.saveAll(roles);
            /* Create a default user instance */
            List<User> users = new ArrayList<>();
            users.add(new User("Jose da Silva", "admin@admin.com", encoder.encode("password"), RoleEnum.ADMIN.getValue()));
            users.add(new User("Antonio Carlos", "trial@trial.com", encoder.encode("password"), RoleEnum.TRIAL.getValue()));
            users.add(new User("Frederico de Souza", "editor@editor.com", encoder.encode("password"), RoleEnum.EDITOR.getValue()));
            userRepository.saveAll(users);
        }
    }
}
