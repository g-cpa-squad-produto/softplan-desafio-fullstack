package com.softplan.processos.features.atribuicoes;

import com.softplan.processos.features.processos.Processo;
import com.softplan.processos.features.usuarios.Usuario;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "ATRIBUICOES", schema = "PUBLIC")
@SequenceGenerator(name = "SEQ_ATRIBUICOES", schema = "PUBLIC", sequenceName = "PUBLIC.SEQ_ATRIBUICOES", initialValue = 1, allocationSize = 1)
@Setter
@Getter
public class Atribuicao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ATRIBUICOES")
    @Column(name = "ID")
    private Long id;

    @NotNull(message = "{Atribuicao.processo.NotNull}")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PROCESSO", referencedColumnName = "ID", nullable = false)
    private Processo processo;

    @NotNull(message = "{Atribuicao.usuarioTriador.NotNull}")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO_TRIADOR", referencedColumnName = "ID", nullable = false)
    private Usuario usuarioTriador;

    @NotNull(message = "{Atribuicao.usuarioFinalizador.NotNull}")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO_FINALIZADOR", referencedColumnName = "ID", nullable = false)
    private Usuario usuarioFinalizador;

    @NotNull(message = "{Atribuicao.dhAtribuicao.NotNull}")
    @Column(name = "DH_ATRIBUICAO", nullable = false)
    private LocalDateTime dhAtribuicao;

}
