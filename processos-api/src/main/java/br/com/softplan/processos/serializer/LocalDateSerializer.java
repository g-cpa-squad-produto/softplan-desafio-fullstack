package br.com.softplan.processos.serializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class LocalDateSerializer extends StdSerializer<LocalDate> {

    private static final long serialVersionUID = -678355614357606172L;

    public LocalDateSerializer() {
	super(LocalDate.class);
    }

    @Override
    public void serialize(LocalDate localDate, JsonGenerator generator, SerializerProvider provider)
            throws IOException {
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	generator.writeString(localDate.format(formatter));
    }
}
