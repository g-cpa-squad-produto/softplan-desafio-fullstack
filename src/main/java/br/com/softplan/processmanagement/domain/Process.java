package br.com.softplan.processmanagement.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "app_process")
public class Process implements Serializable {

    private static final long serialVersionUID = 4867056306314387946L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    @Column(columnDefinition = "TEXT")
    private String description;
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.EAGER)
    private UserSystem creator;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "app_usersystem_process", joinColumns = @JoinColumn(name = "id_process"), inverseJoinColumns = @JoinColumn(name = "id_user"))
    private List<UserSystem> userSystems;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public UserSystem getCreator() {
        return creator;
    }

    public void setCreator(UserSystem creator) {
        this.creator = creator;
    }

    public List<UserSystem> getUserSystems() {
        return userSystems;
    }

    public void setUserSystems(List<UserSystem> userSystems) {
        this.userSystems = userSystems;
    }

}
