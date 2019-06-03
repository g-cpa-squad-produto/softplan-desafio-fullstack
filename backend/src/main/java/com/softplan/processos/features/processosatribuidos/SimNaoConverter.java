package com.softplan.processos.features.processosatribuidos;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import static java.util.Objects.nonNull;

@Converter
public class SimNaoConverter implements AttributeConverter<SimNao, String> {

    @Override
    public String convertToDatabaseColumn(SimNao attribute) {
        return nonNull(attribute) ? attribute.getValue() : null;
    }

    @Override
    public SimNao convertToEntityAttribute(String column) {
        return nonNull(column) ? SimNao.of(column) : null;
    }

}
