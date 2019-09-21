package br.com.processos.processo.specification.entity;

import br.com.processos.usuario.specification.entity.Usuario;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Parecer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pr_id", nullable = false)
    private Processo processo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "us_id", nullable = false)
    private Usuario usuario;

    @Column(length = 4000)
    private String texto;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnumParecerSituacao situacao;

    @NotNull
    @Column(name = "data_criacao", nullable = false)
    private Date dataCriacao;

    @Column(name = "data_atualizacao")
    private Date dataAtualizacao;

}
