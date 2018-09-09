package br.com.softplan.shared;

import java.util.Map;

public interface EnumJsonExport {

    static String getCodigoEnumFromJson(Object json) {
        String nomeEnum = null;
        if (json != null) {
            nomeEnum = json.toString();
            if (json instanceof Map) {
                nomeEnum = (String) ((Map) json).get("codigo");
            }
        }
        return nomeEnum;
    }

    default String getCodigo() {
        return ((Enum) this).name();
    }

}
