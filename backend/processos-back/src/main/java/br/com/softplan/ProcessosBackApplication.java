package br.com.softplan;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.softplan.api.entity.ProfileEnum;
import br.com.softplan.api.entity.User;
import br.com.softplan.api.repository.UserRepository;
/***
 * Método main para iniciar a aplicação
 * @param args
 */
@SpringBootApplication
public class ProcessosBackApplication extends RepositoryRestConfigurerAdapter{

	public static void main(String[] args) {
		SpringApplication.run(ProcessosBackApplication.class, args);
	}
	
    @Bean
    CommandLineRunner init(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            initUsers(userRepository, passwordEncoder);
        };

    }
    
    /**
     * Inicializando os usuários
     * @param userRepository
     * @param passwordEncoder
     */
	private void initUsers(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		/*Salvando um usuário admin*/
        User user = userRepository.findByEmail("admin@softplan.com.br");
        if (user == null) {
        	User admin = new User();
        	admin.setEmail("admin@softplan.com.br");
        	admin.setPassword(passwordEncoder.encode("123456"));
        	admin.setProfile(ProfileEnum.ROLE_ADMIN);
            userRepository.save(admin);
        }
        
        /*Salvando um usuário triador*/
        user = userRepository.findByEmail("triador@softplan.com.br");
        if (user == null) {
        	User triador = new User();
        	triador.setEmail("triador@softplan.com.br");
        	triador.setPassword(passwordEncoder.encode("123456"));
        	triador.setProfile(ProfileEnum.ROLE_TRIADOR);
        	userRepository.save(triador);
        }
        
        /*Salvando um usuário finalizador*/
        user = userRepository.findByEmail("finalizador@softplan.com.br");
        if (user == null) {
        	User triador = new User();
        	triador.setEmail("finalizador@softplan.com.br");
        	triador.setPassword(passwordEncoder.encode("123456"));
        	triador.setProfile(ProfileEnum.ROLE_FINALIZADOR);
        	userRepository.save(triador);
        }
    }
}
