package com.softplan.processesapi.domain.user.subdomains.admin.services;

import com.softplan.processesapi.domain.user.models.User;
import com.softplan.processesapi.domain.user.repository.UserRepository;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@RunWith(PowerMockRunner.class)
class CreateUserServiceTest {

    @InjectMocks
    private CreateUserService createUserService;

    @Mock
    private UserRepository userRepository;

    @Test
    void shouldCallUserRepositoryWithAdminWithParameter() {
        createUserService.createUser(mock(User.class));
        verify(userRepository, times(1)).save(any(User.class));
    }
}