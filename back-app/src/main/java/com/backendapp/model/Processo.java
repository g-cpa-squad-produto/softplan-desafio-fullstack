package com.backendapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Processo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int codigo;
    private String descricao;
    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name="data_criacao")
    private LocalDate dataCriacao;
    @OneToOne
    private Parecer parecer;
    @ManyToMany(mappedBy = "processosAptoParecer")
    private List<Usuario> usuariosAptoParecer;


}
