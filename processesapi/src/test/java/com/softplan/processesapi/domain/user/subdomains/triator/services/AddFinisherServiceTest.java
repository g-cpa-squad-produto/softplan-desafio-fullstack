package com.softplan.processesapi.domain.user.subdomains.triator.services;

import com.softplan.processesapi.domain.process.models.ProcessUser;
import com.softplan.processesapi.domain.process.repository.ProcessRepository;
import com.softplan.processesapi.domain.process.repository.ProcessUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@RunWith(PowerMockRunner.class)
class AddFinisherServiceTest {

    @InjectMocks
    private AddFinisherService addFinisherService;

    @Mock
    private ProcessUserRepository processUserRepository;

    @Test
    void addFinisher() {
        ProcessUser processUser = new ProcessUser();
        addFinisherService.addFinisher(processUser);
        verify(processUserRepository, times(1)).save(processUser);
    }
}