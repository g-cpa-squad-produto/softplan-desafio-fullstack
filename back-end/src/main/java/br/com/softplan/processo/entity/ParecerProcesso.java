package br.com.softplan.processo.entity;

import br.com.softplan.security.entity.Usuario;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "tb_parecer_processo", schema = "softplan")
@SequenceGenerator(name = "seq_parecer_processo", sequenceName = "softplan.seq_parecer_processo")
public class ParecerProcesso {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_parecer_processo")
    @Column(name = "pa_id", nullable = false)
    private Long id;

    @Column(name = "pa_parecer")
    private String parecer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pr_id")
    private Processo processo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "us_id")
    private Usuario usuario;
}
