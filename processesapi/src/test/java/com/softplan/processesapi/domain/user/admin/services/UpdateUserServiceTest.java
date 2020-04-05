package com.softplan.processesapi.domain.user.admin.services;

import com.softplan.processesapi.domain.user.models.User;
import com.softplan.processesapi.domain.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@RunWith(PowerMockRunner.class)
class UpdateUserServiceTest {

    @InjectMocks
    private UpdateUserService updateUserService;

    @Mock
    private UserRepository userRepository;

    @Test
    void put() {
        updateUserService.put(mock(User.class));
        verify(userRepository, times(1)).save(any(User.class));
    }
}