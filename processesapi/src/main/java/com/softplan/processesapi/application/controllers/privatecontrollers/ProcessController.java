package com.softplan.processesapi.application.controllers.privatecontrollers;

import com.softplan.processesapi.domain.process.models.Process;
import com.softplan.processesapi.domain.process.models.ProcessUser;
import com.softplan.processesapi.domain.user.subdomains.finisher.services.IFinishProcessService;
import com.softplan.processesapi.domain.user.subdomains.triator.services.IAddFinisherService;
import com.softplan.processesapi.domain.user.subdomains.triator.services.ICreateProcessService;
import com.softplan.processesapi.domain.user.subdomains.triator.services.IGetProcessService;
import com.softplan.processesapi.infrastructure.responsestatus.ResourceNotFoundException;
import com.softplan.processesapi.infrastructure.responsestatus.WrongCredentialsException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/processes")
public class ProcessController {

    private IGetProcessService getProcessService;
    private IAddFinisherService addFinisherService;
    private ICreateProcessService createProcessService;
    private IFinishProcessService finishProcessService;

    public ProcessController(IGetProcessService getProcessService, IAddFinisherService addFinisherService,
                             ICreateProcessService createProcessService, IFinishProcessService finishProcessService) {
        this.getProcessService = getProcessService;
        this.addFinisherService = addFinisherService;
        this.createProcessService = createProcessService;
        this.finishProcessService = finishProcessService;
    }

    @GetMapping("/")
    public List<Process> getAll() {
        return getProcessService.getAll();
    }

    @PostMapping("/")
    public Process post(@RequestBody Process process) {
        return createProcessService.createProcess(process);
    }

    @PatchMapping("/addFinisher")
    public ProcessUser addFinisher(@RequestBody ProcessUser processUser) {
        return addFinisherService.addFinisher(processUser);
    }

    @PatchMapping("/finish/{processId}")
    public Process finish(@PathVariable Long processId, @RequestBody String description)
            throws WrongCredentialsException, ResourceNotFoundException {
        Process process = getProcessService.getOne(processId).orElseThrow(
                () -> new ResourceNotFoundException("Not " + "found any process with id " + processId));

        if (description.isEmpty()) {
            throw new WrongCredentialsException("Description is required!");
        }

        return finishProcessService.finishProcess(process, description);
    }
}
