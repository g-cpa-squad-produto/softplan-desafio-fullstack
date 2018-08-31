package com.luanrubensf.challenge.model;

import com.luanrubensf.challenge.core.ChallengeConstants;
import com.luanrubensf.challenge.core.EntityBuilder;
import com.luanrubensf.challenge.core.IEntity;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static com.luanrubensf.challenge.core.ChallengeConstants.GENERATOR_SEQUENCE;

@Entity
@Table(name = "ROLES", schema = ChallengeConstants.SCHEMA)
@GenericGenerator(name = "SEQ_ROLES", strategy = GENERATOR_SEQUENCE,
        parameters = {@Parameter(name = "sequence", value = "SEQ_ROLES"),
                @Parameter(name = "schema", value = ChallengeConstants.SCHEMA)})
public class Role implements IEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ROLES")
    private Long id;

    @NotNull(message = "O nome da ROLE não pode ser nulo")
    @Column(name = "NAME")
    private String name;

    @Override
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

    public static class Builder extends EntityBuilder<Role> {

        private Builder(Role entity, EntityState state) {
            super(entity, state);
        }

        public static Builder create() {
            return new Builder(new Role(), EntityState.NEW);
        }

        public static Builder from(Role entity) {
            return new Builder(entity, EntityState.BUILT);
        }

        public Builder id(Long id) {
            entity.id = id;
            return this;
        }

        public Builder name(String name) {
            entity.name = name;
            return this;
        }
    }
}
