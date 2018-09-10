package br.com.softplan.feature.usuario.model;

import br.com.softplan.shared.EnumJsonExport;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum PerfilUsuario implements EnumJsonExport {

    ADMINISTRADOR("Administrador"),
    TRIADOR("Usuário Triador"),
    FINALIZADOR("Usuário Finalizador");

    private String descricao;

    PerfilUsuario(String descricao) {
        this.descricao = descricao;
    }

    @JsonCreator
    public static PerfilUsuario fromJson(Object json) {
        String nomeEnum = EnumJsonExport.getCodigoEnumFromJson(json);
        if (nomeEnum != null) {
            return PerfilUsuario.valueOf(nomeEnum);
        }
        return null;
    }
}
