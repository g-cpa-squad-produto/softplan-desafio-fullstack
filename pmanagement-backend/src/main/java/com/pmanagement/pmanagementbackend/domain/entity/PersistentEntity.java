package com.pmanagement.pmanagementbackend.domain.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Default Entity class to persist data
 *
 * @author Cristian Souza
 *
 * @version 1.0.0
 * @since 1.0.0, Jun 2, 2019
 */
@Data
@ToString
@MappedSuperclass
@NoArgsConstructor
@EqualsAndHashCode
@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, scope = PersistentEntity.class)
public abstract class PersistentEntity implements Serializable {

    @Id
    @Getter
    @Column(name = "id", unique = true, updatable = false)
    @SequenceGenerator(name = "pooled_sequence_generator", sequenceName = "pooled_sequence_generator", allocationSize = 5)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pooled_sequence_generator")
    private Long id;

    @Getter
    @Column(name = "created_on", nullable = false)
    private LocalDateTime createdOn;
    @Getter
    @Column(name = "updated_on")
    private LocalDateTime updatedOn;

    /**
     * Set the date of creation for this entity
     */
    @PrePersist
    protected void beforeInsert() {
        this.createdOn = LocalDateTime.now();
    }

    /**
     * Set the date of update for this entity
     */
    @PreUpdate
    protected void beforeUpdate() {
        this.updatedOn = LocalDateTime.now();
    }
}