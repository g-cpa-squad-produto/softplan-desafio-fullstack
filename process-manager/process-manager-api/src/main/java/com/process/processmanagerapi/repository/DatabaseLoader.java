package com.process.processmanagerapi.repository;

import com.process.processmanagerapi.entity.Process;
import com.process.processmanagerapi.entity.User;
import com.process.processmanagerapi.entity.UserType;
import com.process.processmanagerapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

//@Component
public class DatabaseLoader implements CommandLineRunner {

    private final UserTypeRepository userTypeRepository;
    private final UserRepository userRepository;
    private final ProcessRepository processRepository;

    @Autowired
    public DatabaseLoader(UserTypeRepository userTypeRepo, UserRepository userRepo, ProcessRepository processRepo) {
        this.userTypeRepository = userTypeRepo;
        this.userRepository = userRepo;
        this.processRepository = processRepo;
    }

    @Override
    public void run(String... strings) throws Exception {
        UserType type1 = new UserType(1, UserService.ADMIN_TYPE);
        UserType type2 = new UserType(2, UserService.TRIADOR_USER);
        UserType type3 = new UserType(3, UserService.FINISHER_USER);
        this.userTypeRepository.save(type1);
        this.userTypeRepository.save(type2);
        this.userTypeRepository.save(type3);

/*        userRepository.save(new User(1, "userNameAdmin", "passTeste", new Date(), "testUser", type1));
        userRepository.save(new User(2, "userNameTriador", "passTeste", new Date(), "testUser", type2));
        userRepository.save(new User(3, "userNameFinalizador", "passTeste", new Date(), "testUser", type3));*/


        this.processRepository.save(new Process(1, "description test", new Date(), "system"));

    }

}

