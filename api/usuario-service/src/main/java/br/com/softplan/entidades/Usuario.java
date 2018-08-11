package br.com.softplan.entidades;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "usuario")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "codigo")
    @SequenceGenerator(name = "usuario_codigo_seq", sequenceName = "usuario_codigo_seq", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_codigo_seq")
    private Integer codigo;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "observacao")
    private String observacao;

    @ManyToMany
    @JoinTable(name = "perfil_usuario",
               joinColumns = {@JoinColumn(name = "codigo_usuario")},
               inverseJoinColumns = {@JoinColumn(name = "codigo_perfil")})
    private List<Perfil> perfis;
}
