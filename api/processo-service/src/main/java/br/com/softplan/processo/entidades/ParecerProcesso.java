package br.com.softplan.processo.entidades;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "parecer_processo")
public class ParecerProcesso {

    @Id
    @Column(name = "codigo")
    @SequenceGenerator(name = "parecer_processo_codigo_seq", sequenceName = "parecer_processo_codigo_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parecer_processo_codigo_seq")
    private Long codigo;

    @Column(name = "status_parecer")
    @Enumerated(EnumType.STRING)
    private ParecerStatusEnum status;

    @Column(name = "descricao_parecer")
    private String descricaoParecer;

    @OneToMany
    @JoinTable(name = "usuario_parecer", joinColumns = {@JoinColumn(name = "codigo_parecer")},
                                        inverseJoinColumns = {@JoinColumn(name = "codigo_usuario")})
    private List<Usuario> usuarios;
}
