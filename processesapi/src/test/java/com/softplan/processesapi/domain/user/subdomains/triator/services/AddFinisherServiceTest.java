package com.softplan.processesapi.domain.user.subdomains.triator.services;

import com.softplan.processesapi.domain.process.models.Process;
import com.softplan.processesapi.domain.process.models.ProcessUser;
import com.softplan.processesapi.domain.process.repository.ProcessRepository;
import com.softplan.processesapi.domain.process.repository.ProcessUserRepository;
import com.softplan.processesapi.domain.user.models.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@RunWith(PowerMockRunner.class)
class AddFinisherServiceTest {

    @InjectMocks
    private AddFinisherService addFinisherService;

    @Mock
    private ProcessRepository processRepository;

    @Test
    void addFinisher() {
        Process process = new Process();
        User finisher = new User();
        addFinisherService.addFinisher(process, finisher);
        verify(processRepository, times(1)).save(any(Process.class));
    }
}