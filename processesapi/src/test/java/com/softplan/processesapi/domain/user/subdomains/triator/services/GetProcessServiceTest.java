package com.softplan.processesapi.domain.user.subdomains.triator.services;

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
class GetProcessServiceTest {

    @InjectMocks
    private GetProcessService getProcessService;

    @Mock
    private ProcessRepository processRepository;

    @Test
    void getAll() {
        getProcessService.getAll();
        verify(processRepository, times(1)).findAll();
    }

    @Test
    void getOne() {
        Long processId = Long.MAX_VALUE;
        getProcessService.getOne(processId);
        verify(processRepository, times(1)).findById(processId);
    }
}