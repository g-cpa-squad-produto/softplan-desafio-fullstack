package br.com.softplan.processmanagement.config;

import br.com.softplan.processmanagement.domain.Process;
import br.com.softplan.processmanagement.domain.UserSystem;
import br.com.softplan.processmanagement.domain.UserType;
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
    private UsersService usersService;

    @Autowired
    private ProcessesService processesService;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public void run(String... args) throws Exception {

        UserSystem userSystemAdmin = new UserSystem();
        userSystemAdmin.setName("Administrador");
        userSystemAdmin.setType(UserType.ADMIN);
        userSystemAdmin.setPassword(bcryptEncoder.encode("123456"));
        userSystemAdmin.setEmail("admin@email.com");
        usersService.save(userSystemAdmin);

        UserSystem userSystemTriador = new UserSystem();
        userSystemTriador.setName("Triador");
        userSystemTriador.setType(UserType.TRIADOR);
        userSystemTriador.setPassword(bcryptEncoder.encode("123456"));
        userSystemTriador.setEmail("triador@email.com");
        usersService.save(userSystemTriador);

        UserSystem userSystemFinalizador = new UserSystem();
        userSystemFinalizador.setName("Finalizador");
        userSystemFinalizador.setType(UserType.FINALIZADOR);
        userSystemFinalizador.setPassword(bcryptEncoder.encode("123456"));
        userSystemFinalizador.setEmail("finalizador@email.com");
        usersService.save(userSystemFinalizador);

        UserSystem userSystemFinalizador2 = new UserSystem();
        userSystemFinalizador2.setName("Segundo finalizador");
        userSystemFinalizador2.setType(UserType.FINALIZADOR);
        userSystemFinalizador2.setPassword(bcryptEncoder.encode("123456"));
        userSystemFinalizador2.setEmail("finalizador2@email.com");
        usersService.save(userSystemFinalizador2);

        // PROCESS
        Process process1 = new Process();
        process1.setCode("1234");
        process1.setCreatedAt(LocalDateTime.now());
        process1.setCreator(userSystemTriador);
        process1.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur porttitor ante at felis interdum egestas. In et purus mauris. Duis sed suscipit velit. Curabitur eu elementum ligula, ut lacinia magna. Aliquam auctor finibus aliquam. Fusce consequat sit amet felis ut luctus. Proin dictum luctus nibh, et vulputate risus semper id. Vivamus vel eros cursus, mattis felis quis, vestibulum turpis. Vivamus luctus, risus quis porta convallis, dui sem lacinia velit, eu maximus risus sem sit amet erat.");
        process1.setUserSystems(Arrays.asList(new UserSystem[]{userSystemFinalizador}));
        processesService.save(process1);

        Process process2 = new Process();
        process2.setCode("4321");
        process2.setCreatedAt(LocalDateTime.now());
        process2.setCreator(userSystemTriador);
        process2.setDescription("Curabitur porttitor ante at felis interdum egestas. In et purus mauris. Duis sed suscipit velit. Curabitur eu elementum ligula, ut lacinia magna. Aliquam auctor finibus aliquam. Fusce consequat sit amet felis ut luctus. Proin dictum luctus nibh, et vulputate risus semper id. Vivamus vel eros cursus, mattis felis quis, vestibulum turpis. Vivamus luctus, risus quis porta convallis, dui sem lacinia velit, eu maximus risus sem sit amet erat.");
        process2.setUserSystems(Arrays.asList(new UserSystem[]{userSystemFinalizador2}));
        processesService.save(process2);

        Process process3 = new Process();
        process3.setCode("09876");
        process3.setCreatedAt(LocalDateTime.now());
        process3.setCreator(userSystemTriador);
        process3.setDescription("In et purus mauris. Duis sed suscipit velit. Curabitur eu elementum ligula, ut lacinia magna. Aliquam auctor finibus aliquam. Fusce consequat sit amet felis ut luctus. Proin dictum luctus nibh, et vulputate risus semper id. Vivamus vel eros cursus, mattis felis quis, vestibulum turpis. Vivamus luctus, risus quis porta convallis, dui sem lacinia velit, eu maximus risus sem sit amet erat.");
        process3.setUserSystems(Arrays.asList(new UserSystem[]{userSystemFinalizador2}));
        processesService.save(process3);


    }
}
