package com.example.demo.entity;

import com.example.demo.entity.enumeration.Status;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Um processo
 * @author Dioiro
 */
@Entity
@Getter @Setter
public class Process implements Serializable {
    private static final long serialVersionUID = 7094656677576873348L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "process", cascade = CascadeType.PERSIST)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Report> reports = new HashSet<>();

    @ManyToOne
    private User autor;

    @JsonView
    private Boolean isComplete(){
        return reports.stream()
                .noneMatch(report -> report.getStatus()== Status.PENDENTE);
    }
}
