package br.com.softplan.processo.dto;

import br.com.softplan.processo.entity.Processo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProcessoDTO {

    private Long id;
    private String numero;
    private String descricao;

    public ProcessoDTO() {
    }

    public ProcessoDTO(Processo processo) {
        this.id = processo.getId();
        this.numero = processo.getNumero();
        this.descricao = processo.getDescricao();
    }
}
