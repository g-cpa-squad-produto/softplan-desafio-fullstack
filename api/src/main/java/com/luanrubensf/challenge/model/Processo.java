package com.luanrubensf.challenge.model;

import com.luanrubensf.challenge.core.ChallengeConstants;
import com.luanrubensf.challenge.core.EntityBuilder;
import com.luanrubensf.challenge.core.IEntity;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static com.luanrubensf.challenge.core.ChallengeConstants.GENERATOR_SEQUENCE;

/**
 * Entide que define o Processo
 */
@Entity
@Table(name = "PROCESSOS", schema = ChallengeConstants.SCHEMA)
@GenericGenerator(name = "SEQ_PROCESSOS", strategy = GENERATOR_SEQUENCE,
        parameters = {@org.hibernate.annotations.Parameter(name = "sequence", value = "SEQ_PROCESSOS"),
                @org.hibernate.annotations.Parameter(name = "schema", value = ChallengeConstants.SCHEMA)})
public class Processo implements IEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PROCESSOS")
    private Long id;

    @NotNull(message = "O nome do processo não pode ser nulo")
    @Column(name = "NAME")
    @Length(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    private String name;

    @NotNull(message = "A descrição do processo não pode ser nulo")
    @Column(name = "DESCRIPTION")
    @Length(max = 1000, message = "A descricao deve ter no máximo 1000 caracteres")
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static class Builder extends EntityBuilder<Processo> {

        private Builder(Processo entity, EntityState state) {
            super(entity, state);
        }

        public static Builder create() {
            return new Builder(new Processo(), EntityState.NEW);
        }

        public static Builder from(Processo entity) {
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

        public Builder description(String description) {
            entity.description = description;
            return this;
        }
    }
}
