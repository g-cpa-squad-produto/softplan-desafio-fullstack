package br.com.softplan.processmanagement.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name="app_opinion")
public class Opinion implements Serializable {

    private static final long serialVersionUID = -3818490191704957608L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "id_process")
    private Process process;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "id_user")
    private UserSystem userSystem;

    @Column(columnDefinition = "TEXT")
    private String text;

    private LocalDateTime createdAt;

    public Opinion() {
    }

    public Opinion(Process process, UserSystem userSystem, String text, LocalDateTime createdAt) {
        this.process = process;
        this.userSystem = userSystem;
        this.text = text;
        this.createdAt = createdAt;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    public UserSystem getUserSystem() {
        return userSystem;
    }

    public void setUserSystem(UserSystem userSystem) {
        this.userSystem = userSystem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Opinion opinion = (Opinion) o;
        return Objects.equals(id, opinion.id) && Objects.equals(process, opinion.process) && Objects.equals(userSystem, opinion.userSystem) && Objects.equals(text, opinion.text) && Objects.equals(createdAt, opinion.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, process, userSystem, text, createdAt);
    }

}
