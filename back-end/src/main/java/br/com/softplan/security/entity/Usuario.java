package br.com.softplan.security.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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
import javax.persistence.Transient;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tb_usuario", schema = "softplan")
@SequenceGenerator(name = "seq_usuario", sequenceName = "softplan.seq_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_usuario")
    @Column(name = "us_id", nullable = false)
    private Long id;

    @Column(name = "us_email")
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "us_senha")
    private String senha;

    @Column(name = "us_nome")
    private String nome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pp_id")
    private Papel papel;

    @Transient
    private List<String> permissoes;

}
