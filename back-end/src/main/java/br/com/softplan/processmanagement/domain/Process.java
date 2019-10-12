package br.com.softplan.processmanagement.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cascade;

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

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    private UserSystem creator;

    @OneToMany(mappedBy = "process", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Opinion> opinions;

    public Process() {
    }

    public Process(String code, String description, LocalDateTime createdAt, UserSystem creator) {
        this.code = code;
        this.description = description;
        this.createdAt = createdAt;
        this.creator = creator;
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

    public List<Opinion> getOpinions() {
        return opinions;
    }

    public void setOpinions(List<Opinion> opinions) {
        this.opinions = opinions;
    }

    @Override
    public String toString() {
        return "Process{" + "id=" + id + ", code='" + code + '\'' + ", description='" + description + '\'' + ", createdAt=" + createdAt + ", creator=" + creator + ", opinions=" + opinions + '}';
    }
}
