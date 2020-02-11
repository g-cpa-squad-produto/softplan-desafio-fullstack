package com.pmanagement.pmanagementbackend.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * The class to hold the User data
 *
 * @author Cristian Souza
 *
 * @version 1.0.0
 * @since 1.0.0, Jun 2, 2019
 */
@Entity
@Table(name = "user")
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class User extends PersistentEntity implements UserDetails {

    /**
     * The max of attempts to login
     */
    private static final int MAX_ATTEMPTS = 5;

    @Getter
    @Setter
    @NotBlank
    @Column(nullable = false)
    private String name;

    @Getter
    @Setter
    @NotBlank
    @Column(nullable = false, unique = true)
    private String email;

    @Getter
    @Setter
    @NotBlank
    @Column(nullable = false, unique = true)
    private String username;

    @Getter
    @Setter
    @NotNull
    @Column(nullable = false)
    private boolean status;

    @Getter
    @Setter
    @Column
    @JsonIgnore
    private String password;

    @Getter
    @Setter
    @Column
    private Integer loginAttemps;

    @Getter
    @Setter
    @Column
    private LocalDateTime lastLoginAttemp;

    @Getter
    @Setter
    @Column
    private LocalDateTime lastAcess;

    /**
     * Register the login attempts
     */
    public void registerFailedLogin() {
        this.loginAttemps = this.loginAttemps != null ? this.loginAttemps + 1 : 1;
        this.lastLoginAttemp = LocalDateTime.now();
    }

    /**
     * {@inheritDoc }
     * 
     * @return
     */
    @Override
    @Transient
    @JsonIgnore
    public boolean isAccountNonLocked() {
        if (this.lastLoginAttemp == null || this.loginAttemps == null) {
            return true;
        }

        if (this.loginAttemps >= MAX_ATTEMPTS) {
            if (LocalDateTime.now().isBefore(this.lastLoginAttemp.plusHours(3))) {
                return false;
            } else {
                return true;
            }
        }

        return true;
    }

    /**
     * {@inheritDoc }
     * 
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    /**
     * {@inheritDoc }
     * 
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * {@inheritDoc }
     * 
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * {@inheritDoc }
     * 
     * @return
     */
    @Override
    public boolean isEnabled() {
        return this.status;
    }
}