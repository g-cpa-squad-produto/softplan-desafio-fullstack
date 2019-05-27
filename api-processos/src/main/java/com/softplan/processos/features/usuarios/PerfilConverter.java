package com.softplan.processos.features.usuarios;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import static java.util.Objects.nonNull;

@Converter
public class PerfilConverter implements AttributeConverter<Perfil, String> {

    @Override
    public String convertToDatabaseColumn(Perfil attribute) {
        return nonNull(attribute) ? attribute.getValue() : null;
    }

    @Override
    public Perfil convertToEntityAttribute(String column) {
        return nonNull(column) ? Perfil.of(column) : null;
    }

}
