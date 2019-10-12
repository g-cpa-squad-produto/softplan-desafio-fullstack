package br.com.softplan.processmanagement.config;

import br.com.softplan.processmanagement.domain.*;
import br.com.softplan.processmanagement.domain.Process;
import br.com.softplan.processmanagement.repositories.ProcessesRepository;
import br.com.softplan.processmanagement.repositories.OpinionRepository;
import br.com.softplan.processmanagement.repositories.UsersSystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;

@Configuration
public class Initializer implements CommandLineRunner {

    @Autowired
    private UsersSystemRepository usersSystemRepository;

    @Autowired
    private OpinionRepository opinionRepository;

    @Autowired
    private ProcessesRepository processesRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        UserSystem userSystemAdmin = new UserSystem("Administrador","admin@email.com",bcryptEncoder.encode("123456"), UserType.ADMIN);
        UserSystem userSystemTriador = new UserSystem("Triador","triador@email.com",bcryptEncoder.encode("123456"), UserType.TRIADOR);
        UserSystem userSystemFinalizador = new UserSystem("Finalizador","finalizador@email.com",bcryptEncoder.encode("123456"), UserType.FINALIZADOR);
        UserSystem userSystemFinalizador2 = new UserSystem("Segundo finalizador","finalizador2@email.com",bcryptEncoder.encode("123456"), UserType.FINALIZADOR);
        usersSystemRepository.saveAll(Arrays.asList(userSystemAdmin,userSystemTriador,userSystemFinalizador,userSystemFinalizador2));

        // PROCESS
        Process process1 = new Process("12","Aqui vai a descrição",LocalDateTime.now(),userSystemTriador);
        Process process2 = new Process("23","Aqui vai a descrição",LocalDateTime.now(),userSystemTriador);
        Process process3 = new Process("45","Aqui vai a descrição",LocalDateTime.now(),userSystemTriador);
        processesRepository.saveAll(Arrays.asList(process1,process2,process3));

        Opinion opinion1 = new Opinion(process1, userSystemFinalizador, "Parecer 1", LocalDateTime.now());
        Opinion opinion2 = new Opinion(process2, userSystemFinalizador, "Parecer 2", LocalDateTime.now());
        Opinion opinion3 = new Opinion(process2, userSystemFinalizador2, "Parecer 3", LocalDateTime.now());
        Opinion opinion4 = new Opinion(process3, userSystemFinalizador2, "Parecer 4", LocalDateTime.now());

        opinionRepository.saveAll(Arrays.asList(opinion1,opinion2,opinion3,opinion4));


    }
}
