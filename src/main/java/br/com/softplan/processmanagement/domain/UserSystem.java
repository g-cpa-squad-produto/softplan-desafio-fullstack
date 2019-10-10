package br.com.softplan.processmanagement.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "app_user")
public class UserSystem implements Serializable {

    private static final long serialVersionUID = 8822177810307774279L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    @JsonIgnore
    private String password;

    @Enumerated(EnumType.STRING)
    private UserType type;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "creator")
    private List<Process> processesCreated;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "app_usersystem_process", joinColumns = @JoinColumn(name = "id_user"), inverseJoinColumns = @JoinColumn(name = "id_process"))
    private List<Process> processes;

    public UserSystem() {
    }

    public UserSystem(String name, String email, String password, UserType type) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.type = type;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public List<Process> getProcesses() {
        return processes;
    }

    public void setProcesses(List<Process> processes) {
        this.processes = processes;
    }

    public List<Process> getProcessesCreated() {
        return processesCreated;
    }

    public void setProcessesCreated(List<Process> processesCreated) {
        this.processesCreated = processesCreated;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
