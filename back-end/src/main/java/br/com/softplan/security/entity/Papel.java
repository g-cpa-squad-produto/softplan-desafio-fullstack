package br.com.softplan.security.entity;

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
@Table(name = "tb_papel", schema = "softplan")
@SequenceGenerator(name = "seq_papel", sequenceName = "softplan.seq_papel")
public class Papel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_papel")
    @Column(name = "pp_id", nullable = false)
    private Long id;

    @Column(name = "pp_descricao")
    private String descricao;

}
