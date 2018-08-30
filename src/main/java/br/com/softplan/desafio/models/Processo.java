package br.com.softplan.desafio.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_processo")
    private Long codigo;

    @NotNull
    @Size(min = 1)
    @Column(name="nr_processo")
    private String numero;

    @Column(name="ds_parecer")
    private String parecer;

    @Enumerated(EnumType.STRING)
    @Column(name="in_status")
    private Status status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="processo_usuario",
            joinColumns={@JoinColumn(name="cd_processo")},
            inverseJoinColumns={@JoinColumn(name="cd_usuario")})
    private List<Usuario> usuarios = new ArrayList<>();

    @OneToOne
    @JoinColumn(name= "cd_usuario_finalizador")
    private Usuario usuarioFinalizador;

    @Column(name="dt_cadastro")
    private LocalDate dataCadastro;

    @Column(name="dt_finalizado")
    private LocalDate dataFinalizado;

    public void adicionaUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

}
