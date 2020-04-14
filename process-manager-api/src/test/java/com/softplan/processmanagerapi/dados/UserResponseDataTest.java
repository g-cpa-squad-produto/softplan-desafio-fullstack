package com.softplan.processmanagerapi.dados;

import com.softplan.processmanagerapi.models.Role;
import com.softplan.processmanagerapi.models.User;
import com.softplan.processmanagerapi.models.enums.RoleType;
import com.softplan.processmanagerapi.payload.SignUpRequest;

import java.util.Arrays;
import java.util.HashSet;

public class UserResponseDataTest {

    public static User userAdmin() {
        User user = new User();
        user.setEmail("joaohonorato@gmail.com");
        user.setName("João Gabriel Honorato de Assis");
        user.setPassword("secret");
        user.setRoles(new HashSet<>(Arrays.asList(new Role(RoleType.ROLE_ADMIN))));
        user.setUsername("joassis");
        return user;
    }

    public static SignUpRequest signUpAdmin() {
        SignUpRequest user = new SignUpRequest();
        user.setEmail("joaohonorato@gmail.com");
        user.setName("João Gabriel Honorato de Assis");
        user.setPassword("secret");
        user.setRole(Arrays.asList(RoleType.ROLE_ADMIN));
        user.setUsername("joassis");
        return user;
    }

}
