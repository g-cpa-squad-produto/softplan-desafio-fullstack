package com.softplan.processmanagerapi.factory;

import com.softplan.processmanagerapi.models.Process;
import com.softplan.processmanagerapi.models.User;
import com.softplan.processmanagerapi.payload.response.ProcessResponse;
import com.softplan.processmanagerapi.payload.UserSummary;
import org.springframework.stereotype.Component;

@Component
public class ProcessFactory {
    public Process getEntity(ProcessResponse processResponse) {
        Process process = new Process();
        process.setAssignedUsers(processResponse.getAssignedUsers());
        process.setId(processResponse.getId());
        process.setOpinions(processResponse.getOpinions());
        process.setCreatedAt(processResponse.getCreationDateTime());
        return process;
    }

    public ProcessResponse getProcessResponse(Process process, User creator) {
        ProcessResponse processResponse = new ProcessResponse();
        processResponse.setAssignedUsers(process.getAssignedUsers());
        processResponse.setCreationDateTime(process.getCreatedAt());
        processResponse.setStatus(process.getStatus());
        processResponse.setSubstantiation(process.getSubstantiation());
        processResponse.setId(process.getId());
        processResponse.setOpinions(process.getOpinions());
        processResponse.setUserSummary(new UserSummary(creator.getId(),creator.getUsername(), creator.getName()));
        return processResponse;
    }
}
