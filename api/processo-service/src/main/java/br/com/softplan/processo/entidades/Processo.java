package br.com.softplan.processo.entidades;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "processo")
public class Processo {

    @Id
    @Column(name = "codigo")
    @SequenceGenerator(name = "processo_codigo_seq", sequenceName = "processo_codigo_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "processo_codigo_seq")
    private Long codigo;

    @Column(name = "nome_advogado")
    private String nomeAdvogado;

    @Column(name = "assunto")
    private String assunto;

    @Column(name = "descricao")
    private String descricao;

    @OneToOne(cascade = CascadeType.ALL)
    private ParecerProcesso parecer;
}
