package com.softplan.processmanagerapi.factory;

import com.softplan.processmanagerapi.models.Process;
import com.softplan.processmanagerapi.payload.request.ProcessRequest;
import com.softplan.processmanagerapi.payload.response.ProcessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ProcessFactory {

    @Autowired
    private UserFactory userFactory;

    @Autowired
    private OpinionFactory opinionFactory;

    public Process getEntity(ProcessRequest processRequest) {
        Process process = new Process();
        process.setTitle(processRequest.getTitle());
        process.setSubstantiation(processRequest.getSubstantiation());
        process.setStatus(processRequest.getStatus());
        return process;
    }

    public ProcessResponse getProcessResponse(Process process) {
        ProcessResponse processResponse = new ProcessResponse();
        processResponse.setTitle(process.getTitle());
        processResponse.setAssignedUsers(process.getAssignedUsers().stream().map(userFactory::getUserResponse).collect(Collectors.toSet()));
        processResponse.setCreationDateTime(process.getCreatedAt());
        processResponse.setStatus(process.getStatus());
        processResponse.setSubstantiation(process.getSubstantiation());
        processResponse.setId(process.getId());
        return processResponse;
    }
}
