package com.softplan.processesapi.domain.user.subdomains.triator.services;

import com.softplan.processesapi.domain.process.models.Process;
import com.softplan.processesapi.domain.process.repository.ProcessRepository;
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
class CreateProcessServiceTest {

    @InjectMocks
    private CreateProcessService createProcessService;

    @Mock
    private ProcessRepository processRepository;

    @Test
    void createProcess() {
        Process process = new Process();
        createProcessService.createProcess(process);
        verify(processRepository, times(1)).save(process);
    }
}