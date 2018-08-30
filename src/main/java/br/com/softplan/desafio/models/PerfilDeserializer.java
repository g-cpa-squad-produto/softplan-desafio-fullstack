package br.com.softplan.desafio.models;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class PerfilDeserializer extends StdDeserializer<Perfil> {

    private static final long serialVersionUID = 3816677032262703356L;

    public PerfilDeserializer() {
        super(Perfil.class);
    }

    @Override
    public Perfil deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {

        JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);
        JsonNode nameJsonNode = jsonNode.get("name");

        if (nameJsonNode == null) {
            throw new IOException("Invalid format for enum, pass as {\"name\": \"enum\"}");
        }

        return Perfil.valueOf(nameJsonNode.asText());
    }
}
