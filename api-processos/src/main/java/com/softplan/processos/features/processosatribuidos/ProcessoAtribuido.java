package com.softplan.processos.features.processosatribuidos;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Immutable
@Table(name = "VW_PROCESSOS_ATRIBUIDOS", schema = "PUBLIC")
@Getter
public class ProcessoAtribuido {

    @Id
    @Column(name = "ID_ATRIBUICAO")
    private Long idAtribuicao;

    @Column(name = "ID_PROCESSO")
    private Long idProcesso;

    @Column(name = "SUMULA")
    private String sumula;

    @Column(name = "ID_USUARIO_ABERTURA")
    private Long idUsuarioAbertura;

    @Column(name = "DH_ABERTURA")
    private LocalDateTime dhAbertura;

    @Column(name = "ID_USUARIO_TRIADOR")
    private Long idUsuarioTriador;

    @Column(name = "DH_ATRIBUICAO")
    private LocalDateTime dhAtribuicao;

    @Column(name = "ID_USUARIO_FINALIZADOR")
    private Long idUsuarioFinalizador;

    @Convert(converter = SimNaoConverter.class)
    @Column(name = "POSSUI_PARECER")
    private SimNao possuiParecer;

}
