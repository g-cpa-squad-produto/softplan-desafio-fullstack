package br.com.softplan.processo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "tb_processo", schema = "softplan")
@SequenceGenerator(name = "seq_processo", sequenceName = "softplan.seq_processo")
public class Processo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_processo")
    @Column(name = "pr_id", nullable = false)
    private Long id;

    @Column(name = "pr_numero")
    private String numero;

    @Column(name = "pr_descricao")
    private String descricao;
}
