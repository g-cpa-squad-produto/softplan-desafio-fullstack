package com.softplan.processesapi.application.controllers.privatecontrollers;

import com.softplan.processesapi.domain.user.admin.models.Admin;
import com.softplan.processesapi.domain.user.admin.services.ICreateUserService;
import com.softplan.processesapi.domain.user.admin.services.IDeleteUserService;
import com.softplan.processesapi.domain.user.admin.services.IGetUserService;
import com.softplan.processesapi.domain.user.admin.services.IUpdateUserService;
import com.softplan.processesapi.domain.user.enums.UserType;
import com.softplan.processesapi.domain.user.models.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RunWith(PowerMockRunner.class)
class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private ICreateUserService createUserService;

    @Test
    void post() {
        User user = new Admin();
        user = userController.put(user);
    }
}