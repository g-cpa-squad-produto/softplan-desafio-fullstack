package com.softplan.processos.features.pareceres;

import com.softplan.processos.features.atribuicoes.Atribuicao;
import com.softplan.processos.features.processos.Processo;
import com.softplan.processos.features.usuarios.Usuario;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "PARECERES", schema = "PUBLIC")
@SequenceGenerator(name = "SEQ_PARECERES", schema = "PUBLIC", sequenceName = "PUBLIC.SEQ_PARECERES", initialValue = 1, allocationSize = 1)
@Setter
@Getter
public class Parecer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PARECERES")
    @Column(name = "ID")
    private Long id;

    @NotNull(message = "{Parecer.processo.NotNull}")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PROCESSO", referencedColumnName = "ID", nullable = false)
    private Processo processo;

    @NotNull(message = "{Parecer.atribuicao.NotNull}")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ATRIBUICAO", referencedColumnName = "ID", nullable = false)
    private Atribuicao atribuicao;

    @NotNull(message = "{Parecer.usuarioParecer.NotNull}")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO_PARECER", referencedColumnName = "ID", nullable = false)
    private Usuario usuarioParecer;

    @NotNull(message = "{Parecer.textoParecer.NotNull}")
    @Column(name = "TEXTO_PARECER", columnDefinition = "TEXT", nullable = false)
    private String textoParecer;

    @NotNull(message = "{Parecer.dhParecer.NotNull}")
    @Column(name = "DH_PARECER", nullable = false)
    private LocalDateTime dhParecer;

}
