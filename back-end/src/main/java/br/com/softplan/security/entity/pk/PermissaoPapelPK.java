package br.com.softplan.security.entity.pk;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class PermissaoPapelPK implements Serializable {

    @Id
    @Column(name = "pe_id", nullable = false)
    private Long permissaoId;

    @Id
    @Column(name = "pp_id", nullable = false)
    private Long papelId;

    public PermissaoPapelPK() {
    }

    public PermissaoPapelPK(Long permissaoId, Long papelId) {
        this.permissaoId = permissaoId;
        this.papelId = papelId;
    }
}
