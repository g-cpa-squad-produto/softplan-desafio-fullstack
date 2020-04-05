package com.softplan.processesapi.domain.process.models;

import com.softplan.processesapi.domain.user.models.User;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "processes_users")
public class ProcessUser {

    @ManyToOne(fetch = FetchType.LAZY)
    private Process process;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
