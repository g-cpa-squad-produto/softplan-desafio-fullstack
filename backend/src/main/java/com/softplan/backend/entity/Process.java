package com.softplan.backend.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.softplan.backend.enumeration.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
@JsonIdentityInfo(
        generator= ObjectIdGenerators.PropertyGenerator.class,
        property="id")
public class Process {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    private User assign;

}
