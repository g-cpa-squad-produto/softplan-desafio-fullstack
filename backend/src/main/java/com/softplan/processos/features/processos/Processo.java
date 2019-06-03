package com.softplan.processos.features.processos;

import com.softplan.processos.features.usuarios.Usuario;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "PROCESSOS", schema = "PUBLIC")
@SequenceGenerator(name = "SEQ_PROCESSOS", schema = "PUBLIC", sequenceName = "PUBLIC.SEQ_PROCESSOS", initialValue = 1, allocationSize = 1)
@Setter
@Getter
public class Processo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PROCESSOS")
    @Column(name = "ID")
    private Long id;

    @NotNull(message = "{Processo.sumula.NotNull}")
    @Size(max = 500, message = "{Processo.sumula.Size}")
    @Column(name = "SUMULA", length = 500, nullable = false)
    private String sumula;

    @NotNull(message = "{Processo.dhAbertura.NotNull}")
    @Column(name = "DH_ABERTURA", nullable = false)
    private LocalDateTime dhAbertura;

    @NotNull(message = "{Processo.usuarioAbertura.NotNull}")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO_ABERTURA", referencedColumnName = "ID", nullable = false)
    private Usuario usuarioAbertura;

}
