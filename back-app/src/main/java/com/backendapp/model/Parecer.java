package com.backendapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
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
    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name="data_criacao")
    private LocalDate dataCriacao;
    @OneToOne
    private Usuario usuario;
}
