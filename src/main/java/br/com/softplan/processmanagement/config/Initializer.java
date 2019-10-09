package br.com.softplan.processmanagement.config;

import br.com.softplan.processmanagement.domain.Process;
import br.com.softplan.processmanagement.domain.User;
import br.com.softplan.processmanagement.domain.UserType;
import br.com.softplan.processmanagement.services.ProcessesService;
import br.com.softplan.processmanagement.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.Arrays;

@Configuration
public class Initializer implements CommandLineRunner {

    @Autowired
    private UsersService usersService;

    @Autowired
    private ProcessesService processesService;

    @Override
    public void run(String... args) throws Exception {

        User userAdmin = new User();
        userAdmin.setName("Administrador");
        userAdmin.setType(UserType.ADMIN);
        userAdmin.setEmail("admin@email.com");
        usersService.save(userAdmin);

        User userTriador = new User();
        userTriador.setName("Triador");
        userTriador.setType(UserType.TRIADOR);
        userTriador.setEmail("triador@email.com");
        usersService.save(userTriador);

        User userFinalizador = new User();
        userFinalizador.setName("Finalizador");
        userFinalizador.setType(UserType.FINALIZADOR);
        userFinalizador.setEmail("finalizador@email.com");
        usersService.save(userFinalizador);

        User userFinalizador2 = new User();
        userFinalizador2.setName("Segundo finalizador");
        userFinalizador2.setType(UserType.FINALIZADOR);
        userFinalizador2.setEmail("finalizador2@email.com");
        usersService.save(userFinalizador2);

        // PROCESS
        Process process1 = new Process();
        process1.setCode("1234");
        process1.setCreatedAt(LocalDateTime.now());
        process1.setCreator(userTriador);
        process1.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur porttitor ante at felis interdum egestas. In et purus mauris. Duis sed suscipit velit. Curabitur eu elementum ligula, ut lacinia magna. Aliquam auctor finibus aliquam. Fusce consequat sit amet felis ut luctus. Proin dictum luctus nibh, et vulputate risus semper id. Vivamus vel eros cursus, mattis felis quis, vestibulum turpis. Vivamus luctus, risus quis porta convallis, dui sem lacinia velit, eu maximus risus sem sit amet erat.");
        process1.setUsers(Arrays.asList(new User[]{userFinalizador}));
        processesService.save(process1);

        Process process2 = new Process();
        process2.setCode("4321");
        process2.setCreatedAt(LocalDateTime.now());
        process2.setCreator(userTriador);
        process2.setDescription("Curabitur porttitor ante at felis interdum egestas. In et purus mauris. Duis sed suscipit velit. Curabitur eu elementum ligula, ut lacinia magna. Aliquam auctor finibus aliquam. Fusce consequat sit amet felis ut luctus. Proin dictum luctus nibh, et vulputate risus semper id. Vivamus vel eros cursus, mattis felis quis, vestibulum turpis. Vivamus luctus, risus quis porta convallis, dui sem lacinia velit, eu maximus risus sem sit amet erat.");
        process2.setUsers(Arrays.asList(new User[]{userFinalizador2}));
        processesService.save(process2);

        Process process3 = new Process();
        process3.setCode("09876");
        process3.setCreatedAt(LocalDateTime.now());
        process3.setCreator(userTriador);
        process3.setDescription("In et purus mauris. Duis sed suscipit velit. Curabitur eu elementum ligula, ut lacinia magna. Aliquam auctor finibus aliquam. Fusce consequat sit amet felis ut luctus. Proin dictum luctus nibh, et vulputate risus semper id. Vivamus vel eros cursus, mattis felis quis, vestibulum turpis. Vivamus luctus, risus quis porta convallis, dui sem lacinia velit, eu maximus risus sem sit amet erat.");
        process3.setUsers(Arrays.asList(new User[]{userFinalizador2}));
        processesService.save(process3);



    }
}
