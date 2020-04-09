package com.softplan.processmanagerapi.payload.request;

import com.softplan.processmanagerapi.models.Opinion;
import com.softplan.processmanagerapi.models.User;
import com.softplan.processmanagerapi.models.enums.StatusType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

public class ProcessRequest {
    @NotNull
    private Set<Long> assignedUsersIds;
    @NotBlank
    private String substantiation;
    @NotNull
    private StatusType status;
    private List<OpinionRequest> opinions;

    public Set<Long> getAssignedUsersIds() {
        return assignedUsersIds;
    }

    public void setAssignedUsersIds(Set<Long> assignedUsersIds) {
        this.assignedUsersIds = assignedUsersIds;
    }

    public String getSubstantiation() {
        return substantiation;
    }

    public void setSubstantiation(String substantiation) {
        this.substantiation = substantiation;
    }

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    public List<OpinionRequest> getOpinions() {
        return opinions;
    }

    public void setOpinions(List<OpinionRequest> opinions) {
        this.opinions = opinions;
    }
}
