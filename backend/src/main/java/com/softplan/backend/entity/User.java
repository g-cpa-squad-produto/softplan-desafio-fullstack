package com.softplan.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.softplan.backend.enumeration.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String username;

    @NotNull
    private String password;

    private String name;
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "assign")
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<Process> processes = new HashSet<>();
}