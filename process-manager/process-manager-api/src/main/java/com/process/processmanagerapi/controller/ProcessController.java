package com.process.processmanagerapi.controller;

import com.process.processmanagerapi.entity.Process;
import com.process.processmanagerapi.service.ProcessService;
import com.process.processmanagerapi.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(tags = "Process APIs")
@RequestMapping(value = "/process", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProcessController {

    @Autowired
    ProcessService processService;

    @PostMapping(path = "/create")
    @ApiOperation(value = "Create a new process")
    public Process createProcess(final @Valid @RequestBody CreateProcessVO createProcessVO) {
        return processService.createProcess(createProcessVO);
    }

    @PostMapping(path = "/finish")
    @ApiOperation(value = "Finish a specific process by process number")
    public Process finishProcess(final @Valid @RequestBody FinishProcessVO finishProcessVO) {
        return processService.finishProcess(finishProcessVO);
    }

    @PostMapping(path = "/findAll")
    @ApiOperation(value = "Find all process on database")
    public List<Process> findAllProcess(final @Valid @RequestBody ViewAllProcessVO viewAllProcessVO) {
        return processService.getAllProcess(viewAllProcessVO);
    }

    @PostMapping(path = "/findByProcessNumber")
    @ApiOperation(value = "Find a specific process by process number")
    public Process findProcessByProcessNumber(final @Valid @RequestBody ViewProcessByProcessNumberVO viewProcessByProcessNumberVO) {
        return processService.getProcessByProcessNumber(viewProcessByProcessNumberVO);
    }

    @PostMapping(path = "/includeProcessOpinion")
    @ApiOperation(value = "Include a process opinion on process by process number")
    public Process includeProcessOpinion(final @Valid @RequestBody ProcessOpinionVO processOpinionVO) {
        return processService.includeProcessOpinion(processOpinionVO);
    }

    @PostMapping(path = "/authorizeUserToManageProcess")
    @ApiOperation(value = "Authorize an user to manage (include process opinion and finish a process) by user name and process number")
    public Process authorizeUserToManageProcess(final @Valid @RequestBody AuthorizeUserProcessVO authorizeUserProcessVO) {
        return processService.authorizeUserToManageProcess(authorizeUserProcessVO);
    }

    @PostMapping(path = "/unauthorizeUserToManageProcess")
    @ApiOperation(value = "Unauthorize an user to manage (include process opinion and finish a process) by user name and process number")
    public Process unauthorizeUserToManageProcess(final @Valid @RequestBody UnauthorizedUserProcessVO unauthorizedUserProcessVO) {
        return processService.unauthorizeUserToManageProcess(unauthorizedUserProcessVO);
    }

}
