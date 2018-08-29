package br.com.softplan.desafio.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="processo")
public class Processo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long codigo;

    private String parecer;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Usuario> usuarios = new ArrayList<>();

    private LocalDate dataCadastro;

    private Usuario usuarioFinalizacao;

    private LocalDate dataFinalizacao;

    public void adicionaUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

}
