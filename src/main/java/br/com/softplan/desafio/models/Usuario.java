package br.com.softplan.desafio.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode(of = { "codigo", "nome" })
@NoArgsConstructor
@Entity
@Table(name="usuario")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    private String nome;

    @Enumerated(EnumType.STRING)
    private Perfil perfil;

    private LocalDate dataCadastro;

    private LocalDate dataAlteracao;

    private LocalDate dataExclusao;

    public Usuario(String nome, Perfil perfil) {
        this.nome = nome;
        this.perfil = perfil;
    }

}
