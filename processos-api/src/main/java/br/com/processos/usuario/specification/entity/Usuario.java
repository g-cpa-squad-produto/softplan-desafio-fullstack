package br.com.processos.usuario.specification.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    @Column(nullable = false, length = 250)
    private String nome;

    @NotNull
    @NotEmpty
    @Column(nullable = false, length = 250)
    private String email;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 32)
    private EnumTipoUsuario tipo;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "situacao", length = 32)
    private EnumSituacaoUsuario situacao;

}
