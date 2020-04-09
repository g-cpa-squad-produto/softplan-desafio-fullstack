package com.softplan.processmanagerapi.models;

import com.softplan.processmanagerapi.models.audit.DateAudit;
import com.softplan.processmanagerapi.models.audit.UserDateAudit;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "processes")
public class Process extends UserDateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @JoinTable(name="user_process",
            joinColumns = @JoinColumn(name = "process_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @BatchSize(size = 30)
    private Set<User> assignedUsers = new HashSet<>();

    @OneToMany(mappedBy = "process", cascade = CascadeType.ALL)
    private List<Opinion> opinions = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
