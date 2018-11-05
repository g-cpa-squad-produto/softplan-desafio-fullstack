package com.process.processmanagerapi.controller;

import com.process.processmanagerapi.entity.Process;
import com.process.processmanagerapi.service.ProcessService;
import com.process.processmanagerapi.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/process")
public class ProcessController {

    @Autowired
    ProcessService processService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Process createProcess(final @RequestBody CreateProcessVO createProcessVO) {
        return processService.createProcess(createProcessVO);
    }

    @RequestMapping(value = "/finish", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Process finishProcess(final @RequestBody FinishProcessVO finishProcessVO) {
        return processService.finishProcess(finishProcessVO);
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Process> findAllProcess(final @RequestBody ViewAllProcessVO viewAllProcessVO) {
        return processService.getAllProcess(viewAllProcessVO);
    }

    @RequestMapping(value = "/findByProcessNumber", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Process findProcessByProcessNumber(final @RequestBody ViewProcessByProcessNumberVO viewProcessByProcessNumberVO) {
        return processService.getProcessByProcessNumber(viewProcessByProcessNumberVO);
    }

    @RequestMapping(value = "/includeProcessOpinion", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Process includeProcessOpinion(final @RequestBody ProcessOpinionVO processOpinionVO) {
        return processService.includeProcessOpinion(processOpinionVO);
    }

    @RequestMapping(value = "/authorizeUserToManageProcess", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Process authorizeUserToManageProcess(final @RequestBody AuthorizeUserProcessVO authorizeUserProcessVO) {
        return processService.authorizeUserToManageProcess(authorizeUserProcessVO);
    }

    @RequestMapping(value = "/unauthorizeUserToManageProcess", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Process unauthorizeUserToManageProcess(final @RequestBody UnauthorizedUserProcessVO unauthorizedUserProcessVO) {
        return processService.unauthorizeUserToManageProcess(unauthorizedUserProcessVO);
    }

}
