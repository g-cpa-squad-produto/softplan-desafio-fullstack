package com.backendapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    @Temporal(TemporalType.DATE)
    @Column(name="data_criacao")
    private Date dataCriacao;
    @OneToOne
    private Parecer Parecer;
    @ManyToMany(mappedBy = "processosAptoParecer")
    private List<Usuario> usuariosAptoParecer;


}
