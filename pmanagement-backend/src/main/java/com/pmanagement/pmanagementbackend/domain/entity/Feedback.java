package com.pmanagement.pmanagementbackend.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * The class to hold the Feedback data
 *
 * @author Cristian Souza
 *
 * @version 1.0.0
 * @since 1.0.0, Jun 2, 2019
 */
@Data
@Entity
@Table(name = "feedback")
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Feedback extends PersistentEntity {

    @NotBlank
    @Column(columnDefinition = "text")
    private String description;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "id_process", nullable = false)
    private Process process;
}