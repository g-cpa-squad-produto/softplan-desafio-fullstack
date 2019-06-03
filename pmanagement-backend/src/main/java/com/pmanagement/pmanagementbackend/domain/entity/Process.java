package com.pmanagement.pmanagementbackend.domain.entity;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * The class to hold the Process data
 *
 * @author Cristian Souza
 *
 * @version 1.0.0
 * @since 1.0.0, Jun 2, 2019
 */
@Data
@Entity
@Table(name = "process")
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Process extends PersistentEntity {

    @NotBlank
    @Column(nullable = false)
    private ProcessStatus status;

    @NotBlank
    @ManyToOne
    @JoinColumn(nullable = false)
    private User createdBy;

    @NotBlank
    @Column(columnDefinition = "text")
    private String description;

    @JoinColumn(name = "id_user")
    @OneToMany(fetch = FetchType.LAZY)
    private Set<User> users;

    @OneToMany(mappedBy = "process", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Feedback> feedbacks;
}
