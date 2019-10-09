package br.com.softplan.processmanagement.domain;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class UserSystemProcessId implements Serializable {

    private static final long serialVersionUID = -6788045775032924656L;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserSystem userSystem;

    @ManyToOne
    @JoinColumn(name = "id_process")
    private Process process;

    public UserSystemProcessId() {
    }

    public UserSystemProcessId(UserSystem userSystem, Process process) {
        this.userSystem = userSystem;
        this.process = process;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public UserSystem getUserSystem() {
        return userSystem;
    }

    public void setUserSystem(UserSystem userSystem) {
        this.userSystem = userSystem;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }
}
