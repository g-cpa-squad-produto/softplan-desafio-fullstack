package com.pmanagement.pmanagementbackend.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * The class to hold the User data
 *
 * @author Cristian Souza
 *
 * @version 1.0.0
 * @since 1.0.0, Jun 2, 2019
 */
@Data
@Entity
@Table(name = "user")
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class User extends PersistentEntity {

    /**
     * The max of attempts to login
     */
    private static final int MAX_ATTEMPTS = 5;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String username;

    @NotNull
    @Column(nullable = false)
    private boolean status;

    @Column
    private String password;

    @Column
    private Integer loginAttemps;

    @Column
    private LocalDateTime lastLoginAttemp;

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
     * Verify if is a valid attempt
     *
     * @return true if is a valid attempt
     */
    @JsonIgnore
    @Transient
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
}