package com.softplan.teste.process.model;

import com.softplan.teste.process.model.audit.UserDateAudit;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "process")
public class Process extends UserDateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 140)
    private String question;
    @Size(max = 140)
    private String status;
    @OneToMany(
            mappedBy = "process",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    @Size(min = 2, max = 6)
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 30)
    private List<Opinion> opinions = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "process_users",
            joinColumns = @JoinColumn(name = "process_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> reviewers = new ArrayList<>();

    @NotNull
    private Instant expirationDateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<Opinion> getOpinions() {
        return opinions;
    }

    public void setOpnions(List<Opinion> opinions) {
        this.opinions = opinions;
    }

    public Instant getExpirationDateTime() {
        return expirationDateTime;
    }

    public void setExpirationDateTime(Instant expirationDateTime) {
        this.expirationDateTime = expirationDateTime;
    }

    public void addOpinion(Opinion opinion) {
        opinions.add(opinion);
        opinion.setProcess(this);
    }

    public void removeOpinion(Opinion opinion) {
        opinions.remove(opinion);
        opinion.setProcess(null);
    }

    public void setReviewers(List<User> reviewers) {
        this.reviewers = reviewers;
    }

    public List<User> getReviewers() {
        return this.reviewers;
    }

    public void addReviewer (User review) {
        reviewers.add(review);
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}