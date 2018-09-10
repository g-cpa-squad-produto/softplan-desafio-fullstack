package br.com.softplan.domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "processo")
public class Processo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Size(min = 2, max = 60)
    @Column(nullable = false, length = 60)
    private String nroprocesso;

    @NotBlank
    @Size(min = 2, max = 60)
    @Column(nullable = false, length = 60)
    private String descricao;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNroprocesso() {
        return nroprocesso;
    }

    public void setNroprocesso(String nroprocesso) {
        this.nroprocesso = nroprocesso;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
