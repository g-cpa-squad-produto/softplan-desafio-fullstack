package com.softplan.processesapi.domain.user.admin.services;

import com.softplan.processesapi.domain.user.admin.models.Admin;
import com.softplan.processesapi.domain.user.finisher.models.Finisher;
import com.softplan.processesapi.domain.user.models.User;
import com.softplan.processesapi.domain.user.repository.UserRepository;
import com.softplan.processesapi.domain.user.triator.models.Triator;
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
        createUserService.createAdmin(mock(Admin.class));
        verify(userRepository, times(1)).save(any(Admin.class));
    }

    @Test
    void shouldCallUserRepositoryWithTriatorWithParameter() {
        createUserService.createTriator(mock(Triator.class));
        verify(userRepository, times(1)).save(any(Triator.class));
    }

    @Test
    void shouldCallUserRepositoryWithFinisherWithParameter() {
        createUserService.createFinisher(mock(Finisher.class));
        verify(userRepository, times(1)).save(any(Finisher.class));
    }
}