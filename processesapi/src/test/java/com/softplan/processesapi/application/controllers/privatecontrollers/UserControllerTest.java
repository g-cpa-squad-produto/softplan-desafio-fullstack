package com.softplan.processesapi.application.controllers.privatecontrollers;

import com.softplan.processesapi.domain.user.subdomains.admin.services.ICreateUserService;
import com.softplan.processesapi.domain.user.models.User;
import com.softplan.processesapi.infrastructure.responsestatus.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.powermock.modules.junit4.PowerMockRunner;


@ExtendWith(MockitoExtension.class)
@RunWith(PowerMockRunner.class)
class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private ICreateUserService createUserService;

    @Test
    void post() throws ResourceNotFoundException {
        User user = new User();
        user = userController.put(user);
    }
}