package br.com.softplan.feature.processo.model;

import br.com.softplan.shared.EnumJsonExport;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum StatusProcesso implements EnumJsonExport {

    NOVO("Novo"),
    AGUARDANDO_PARECER("Aguardando Parecer"),
    FINALIZADO("Finalizado");

    private String descricao;

    StatusProcesso(String descricao) {
        this.descricao = descricao;
    }

    @JsonCreator
    public static StatusProcesso fromJson(Object json) {
        String nomeEnum = EnumJsonExport.getCodigoEnumFromJson(json);
        if (nomeEnum != null) {
            return StatusProcesso.valueOf(nomeEnum);
        }
        return null;
    }
}
