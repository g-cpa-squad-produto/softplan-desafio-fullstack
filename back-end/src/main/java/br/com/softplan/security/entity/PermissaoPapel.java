package br.com.softplan.security.entity;

import br.com.softplan.security.entity.pk.PermissaoPapelPK;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "tb_permissao_papel", schema = "softplan")
@IdClass(PermissaoPapelPK.class)
public class PermissaoPapel {


    @Id
    @Column(name = "pe_id", nullable = false)
    private Long permissaoId;

    @Id
    @Column(name = "pp_id", nullable = false)
    private Long papelId;

    @ManyToOne
    @JoinColumn(name = "pe_id", insertable = false, updatable = false)
    private Permissao permissao;

    @ManyToOne
    @JoinColumn(name = "pp_id", insertable = false, updatable = false)
    private Papel papel;
}
