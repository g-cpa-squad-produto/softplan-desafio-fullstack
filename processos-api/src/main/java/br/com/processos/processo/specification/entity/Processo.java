package br.com.processos.processo.specification.entity;

import br.com.processos.usuario.specification.entity.Usuario;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Processo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    @Column(nullable = false, length = 250)
    private String nome;

    @NotNull
    @NotEmpty
    @Column(nullable = false, length = 4000)
    private String texto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "us_id", nullable = false)
    private Usuario usuarioCriacao;

    @NotNull
    @Column(name = "data_criacao", nullable = false)
    private Date dataCriacao;

}
