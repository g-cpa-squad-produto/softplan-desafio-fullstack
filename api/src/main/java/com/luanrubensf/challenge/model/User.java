package com.luanrubensf.challenge.model;

import com.luanrubensf.challenge.core.ChallengeConstants;
import com.luanrubensf.challenge.core.EntityBuilder;
import com.luanrubensf.challenge.core.IEntity;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Collections;

import static com.luanrubensf.challenge.core.ChallengeConstants.GENERATOR_SEQUENCE;


@Entity
@Table(name = "USERS", schema = ChallengeConstants.SCHEMA)
@GenericGenerator(name = "SEQ_USERS", strategy = GENERATOR_SEQUENCE,
        parameters = {@org.hibernate.annotations.Parameter(name = "sequence", value = "SEQ_USERS"),
                @org.hibernate.annotations.Parameter(name = "schema", value = ChallengeConstants.SCHEMA)})
public class User implements UserDetails, IEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USERS")
    private Long id;

    @NotNull(message = "O nome do usuário não pode ser nulo")
    @Column(name = "NAME")
    @Length(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    private String name;

    @NotNull(message = "O e-mail do usuário não pode ser nulo")
    @Email(message = "Deve ser informado um e-mail válido")
    @Length(max = 100, message = "O e-mail deve ter no máximo 100 caracteres")
    @Column(name = "EMAIL")
    private String email;

    @NotNull(message = "A senha do usuário não pode ser nula")
    @Column(name = "PASSWORD")
    private String password;

    @Transient
    private String matchPassword;

    @Column(name = "ENABLED")
    private Boolean enabled = Boolean.TRUE;

    @ManyToOne
    @JoinColumn(name = "I_ROLES")
    @NotNull(message = "A role do usuário deve ser informada")
    private Role role;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role.getName()));
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchPassword() {
        return matchPassword;
    }

    public void setMatchPassword(String matchPassword) {
        this.matchPassword = matchPassword;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }


    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public static class Builder extends EntityBuilder<User> {

        private Builder(User entity, EntityState state) {
            super(entity, state);
        }

        public static Builder create() {
            return new Builder(new User(), EntityState.NEW);
        }

        public static Builder from(User entity) {
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

        public Builder email(String email) {
            entity.email = email;
            return this;
        }

        public Builder password(String password) {
            entity.password = password;
            return this;
        }

        public Builder matchPassword(String matchPassword) {
            entity.matchPassword = matchPassword;
            return this;
        }

        public Builder enabled(Boolean enabled) {
            entity.enabled = enabled;
            return this;
        }
    }
}
