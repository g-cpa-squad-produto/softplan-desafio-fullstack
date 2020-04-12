package com.softplan.processmanagerapi.payload.response;

import com.softplan.processmanagerapi.models.Opinion;
import com.softplan.processmanagerapi.models.User;
import com.softplan.processmanagerapi.models.enums.StatusType;
import com.softplan.processmanagerapi.payload.UserSummary;

import java.time.Instant;
import java.util.List;
import java.util.Set;

public class ProcessResponse {

    private Long id;
    private String title;
    private StatusType status;
    private UserSummary userSummary;
    private Instant creationDateTime;
    private Set<User> assignedUsers;
    private List<Opinion> opinions;
    private String substantiation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    public UserSummary getUserSummary() {
        return userSummary;
    }

    public void setUserSummary(UserSummary userSummary) {
        this.userSummary = userSummary;
    }

    public Instant getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(Instant creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public Set<User> getAssignedUsers() {
        return assignedUsers;
    }

    public void setAssignedUsers(Set<User> assignedUsers) {
        this.assignedUsers = assignedUsers;
    }

    public List<Opinion> getOpinions() {
        return opinions;
    }

    public void setOpinions(List<Opinion> opinions) {
        this.opinions = opinions;
    }

    public String getSubstantiation() {
        return substantiation;
    }

    public void setSubstantiation(String substantiation) {
        this.substantiation = substantiation;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
