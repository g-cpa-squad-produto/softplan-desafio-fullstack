package com.softplan.processos.features.usuarios;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "USUARIOS", schema = "PUBLIC")
@SequenceGenerator(name = "SEQ_USUARIOS", schema = "PUBLIC", sequenceName = "PUBLIC.SEQ_USUARIOS", initialValue = 1, allocationSize = 1)
@Setter
@Getter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USUARIOS")
    @Column(name = "ID")
    private Long id;

    @NotNull(message = "{Usuario.nome.NotNull}")
    @Size(max = 100, message = "{Usuario.nome.Size}")
    @Column(name = "NOME", length = 100, nullable = false)
    private String nome;

    @NotNull(message = "{Usuario.email.NotNull}")
    @Size(max = 80, message = "{Usuario.email.Size}")
    @Column(name = "EMAIL", length = 80, nullable = false)
    private String email;

    @NotNull(message = "{Usuario.senha.NotNull}")
    @Size(min = 6, max = 64, message = "{Usuario.senha.Size}")
    @Column(name = "SENHA", length = 64, nullable = false)
    private String senha;

    @NotNull(message = "{Usuario.perfil.NotNull}")
    @Convert(converter = PerfilConverter.class)
    @Column(name = "PERFIL", length = 1, nullable = false)
    private Perfil perfil;

    @JsonIgnore
    public boolean isPerfilTriador() {
        return Perfil.TRIADOR.equals(getPerfil());
    }

    @JsonIgnore
    public boolean isPerfilFinalizador() {
        return Perfil.FINALIZADOR.equals(getPerfil());
    }

}
