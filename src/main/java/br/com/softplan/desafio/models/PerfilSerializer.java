package br.com.softplan.desafio.models;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class PerfilSerializer extends StdSerializer<Perfil> {

    public PerfilSerializer() {
        super(Perfil.class, true);
    }

    @Override
    public void serialize(Perfil perfil, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName("name");
        jsonGenerator.writeString(perfil.name());
        jsonGenerator.writeFieldName("descricao");
        jsonGenerator.writeString(perfil.getDescricao());
        jsonGenerator.writeEndObject();
    }

}