package com.example.demo.entity;

import com.example.demo.entity.enumeration.Status;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Um retorno de um usuario
 * @author Dioiro
 */
@Entity
@Getter @Setter
@JsonIdentityInfo(
        generator= ObjectIdGenerators.PropertyGenerator.class,
        property="id")
public class Report implements Serializable {

    private static final long serialVersionUID = -785243798829838703L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status = Status.PENDENTE;;

    @ManyToOne
    private User autor;

    @ManyToOne(cascade = {CascadeType.ALL})
    private Process process;
}
