package com.backendapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Parecer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String texto;
    @Temporal(TemporalType.DATE)
    @Column(name="data_criacao")
    private Date dataCriacao;
    @OneToOne
    private Usuario usuario;
}
