package br.com.softplan.processmanagement.config;

import br.com.softplan.processmanagement.domain.Process;
import br.com.softplan.processmanagement.domain.UserSystem;
import br.com.softplan.processmanagement.domain.UserType;
import br.com.softplan.processmanagement.repositories.ProcessesRepository;
import br.com.softplan.processmanagement.repositories.UsersSystemRepository;
import br.com.softplan.processmanagement.services.ProcessesService;
import br.com.softplan.processmanagement.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Arrays;

@Configuration
public class Initializer implements CommandLineRunner {

    @Autowired
    private UsersSystemRepository usersSystemRepository;

    @Autowired
    private ProcessesRepository processesRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public void run(String... args) throws Exception {

        UserSystem userSystemAdmin = new UserSystem("Administrador","admin@email.com",bcryptEncoder.encode("123456"), UserType.ADMIN);
        UserSystem userSystemTriador = new UserSystem("Triador","triador@email.com",bcryptEncoder.encode("123456"), UserType.TRIADOR);
        UserSystem userSystemFinalizador = new UserSystem("Finalizador","finalizador@email.com",bcryptEncoder.encode("123456"), UserType.FINALIZADOR);
        UserSystem userSystemFinalizador2 = new UserSystem("Segundo finalizador","finalizador2@email.com",bcryptEncoder.encode("123456"), UserType.FINALIZADOR);
        usersSystemRepository.saveAll(Arrays.asList(userSystemAdmin,userSystemTriador,userSystemFinalizador,userSystemFinalizador2));

        // PROCESS
        Process process1 = new Process("12","Aqui vai a descrição",LocalDateTime.now(),userSystemTriador,Arrays.asList(new UserSystem[]{userSystemFinalizador}));
        Process process2 = new Process("23","Aqui vai a descrição",LocalDateTime.now(),userSystemTriador,Arrays.asList(new UserSystem[]{userSystemFinalizador2}));
        Process process3 = new Process("45","Aqui vai a descrição",LocalDateTime.now(),userSystemTriador,Arrays.asList(new UserSystem[]{userSystemFinalizador2}));
        processesRepository.saveAll(Arrays.asList(process1,process2,process3));

    }
}
