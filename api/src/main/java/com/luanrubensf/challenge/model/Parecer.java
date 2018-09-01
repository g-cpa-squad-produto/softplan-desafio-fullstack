package com.luanrubensf.challenge.model;

import com.luanrubensf.challenge.core.ChallengeConstants;
import com.luanrubensf.challenge.core.EntityBuilder;
import com.luanrubensf.challenge.core.IEntity;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static com.luanrubensf.challenge.core.ChallengeConstants.GENERATOR_SEQUENCE;

@Entity
@Table(name = "PARECERES", schema = ChallengeConstants.SCHEMA)
@GenericGenerator(name = "SEQ_PARECERES", strategy = GENERATOR_SEQUENCE,
        parameters = {@org.hibernate.annotations.Parameter(name = "sequence", value = "SEQ_PARECERES"),
                @org.hibernate.annotations.Parameter(name = "schema", value = ChallengeConstants.SCHEMA)})
public class Parecer implements IEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PARECERES")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "I_USERS")
    @NotNull(message = "O usuário deve ser informado")
    private User user;

    @ManyToOne
    @JoinColumn(name = "I_PROCESSOS")
    @NotNull(message = "O processo deve ser informado")
    private Processo processo;

    @Column(name = "PARECER")
    @Length(max = 1000, message = "O parecer deve ter no máximo 1000 caracteres")
    private String parecer;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Processo getProcesso() {
        return processo;
    }

    public void setProcesso(Processo processo) {
        this.processo = processo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getParecer() {
        return parecer;
    }

    public void setParecer(String parecer) {
        this.parecer = parecer;
    }

    public static class Builder extends EntityBuilder<Parecer> {

        private Builder(Parecer entity, EntityState state) {
            super(entity, state);
        }

        public static Builder create() {
            return new Builder(new Parecer(), EntityState.NEW);
        }

        public static Builder from(Parecer entity) {
            return new Builder(entity, EntityState.BUILT);
        }

        public Builder id(Long id) {
            entity.id = id;
            return this;
        }

        public Builder processo(Processo processo) {
            entity.processo = processo;
            return this;
        }

        public Builder user(User user) {
            entity.user = user;
            return this;
        }

        public Builder parecer(String parecer) {
            entity.parecer = parecer;
            return this;
        }
    }
}
