package com.softplan.processesapi.application.controllers.privatecontrollers;

import com.softplan.processesapi.domain.process.models.Process;
import com.softplan.processesapi.domain.process.models.ProcessUser;
import com.softplan.processesapi.domain.user.enums.UserType;
import com.softplan.processesapi.domain.user.models.User;
import com.softplan.processesapi.domain.user.subdomains.admin.services.IGetUserService;
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
import java.util.Optional;

@RestController
@RequestMapping("/processes")
public class ProcessController {

    private IGetProcessService getProcessService;
    private IAddFinisherService addFinisherService;
    private ICreateProcessService createProcessService;
    private IFinishProcessService finishProcessService;
    private IGetUserService getUserService;

    public ProcessController(IGetProcessService getProcessService, IAddFinisherService addFinisherService,
                             ICreateProcessService createProcessService, IFinishProcessService finishProcessService,
                             IGetUserService getUserService) {
        this.getProcessService = getProcessService;
        this.addFinisherService = addFinisherService;
        this.createProcessService = createProcessService;
        this.finishProcessService = finishProcessService;
        this.getUserService = getUserService;
    }

    @GetMapping()
    public List<Process> getAll() {
        return getProcessService.getAll();
    }

    @PostMapping()
    public Process post(@RequestBody Process process) {
        return createProcessService.createProcess(process);
    }

    @PatchMapping("/addFinisher")
    public Process addFinisher(@RequestBody ProcessUser processUser) throws WrongCredentialsException {

        Optional<User> finisher = getUserService.getOne(processUser.getUser().getId());
        Optional<Process> process = getProcessService.getOne(processUser.getProcess().getId());

        if (!process.isPresent()) {
            throw new WrongCredentialsException("This process not exists");
        }

        if (process.get().getDescription() != null && !process.get().getDescription().isEmpty()) {
            throw new WrongCredentialsException("This process is finished");
        }

        if (!finisher.isPresent() || finisher.get().getType() != UserType.FINISHER) {
            throw new WrongCredentialsException("This user not is a finisher");
        }

        Optional<ProcessUser> sameProcessUser =
                getProcessService.getOneByUserAndProcess(process.get(), finisher.get());

        if (sameProcessUser.isPresent()) {
            throw new WrongCredentialsException("This finisher is already in the process");
        }

        return addFinisherService.addFinisher(process.get(), finisher.get());
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
