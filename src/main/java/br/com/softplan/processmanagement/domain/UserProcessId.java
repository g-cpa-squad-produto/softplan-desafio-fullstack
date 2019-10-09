package br.com.softplan.processmanagement.domain;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class UserProcessId implements Serializable {

    private static final long serialVersionUID = -6788045775032924656L;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_process")
    private Process process;

    public UserProcessId() {
    }

    public UserProcessId(User user, Process process) {
        this.user = user;
        this.process = process;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }
}
