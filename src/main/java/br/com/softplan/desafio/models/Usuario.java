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
    @Column(name = "cd_usuario")
    private Long codigo;

    @Column(name="nm_usuario")
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(name="in_perfil")
    private Perfil perfil;

    @Column(name="dt_cadastro")
    private LocalDate dataCadastro;

    @Column(name="dt_alteracao")
    private LocalDate dataAlteracao;

    @Column(name="dt_exclusao")
    private LocalDate dataExclusao;

    public Usuario(String nome, Perfil perfil) {
        this.nome = nome;
        this.perfil = perfil;
    }

    public static UsuarioDTO mapFrom(Usuario usuario) {
        return new UsuarioDTO(usuario.getCodigo(), usuario.getNome(), usuario.getPerfil());
    }
}
