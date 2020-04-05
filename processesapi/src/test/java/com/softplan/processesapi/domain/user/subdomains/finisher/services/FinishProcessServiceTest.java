package com.softplan.processesapi.domain.user.subdomains.finisher.services;

import com.softplan.processesapi.domain.process.models.Process;
import com.softplan.processesapi.domain.process.repository.ProcessRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@RunWith(PowerMockRunner.class)
class FinishProcessServiceTest {

    @InjectMocks
    private FinishProcessService finishProcessService;

    @Mock
    private ProcessRepository processRepository;

    @Mock
    private Process process;

    @Test
    void finishProcess() {
        String description = "Discription test";
        finishProcessService.finishProcess(process, description);

        verify(processRepository, times(1)).save(process);
        verify(process, times(1)).setDescription(description);
    }
}