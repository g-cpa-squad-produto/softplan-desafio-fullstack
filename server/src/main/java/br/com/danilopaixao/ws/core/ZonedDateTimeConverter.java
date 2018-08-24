package br.com.danilopaixao.ws.core;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ZonedDateTimeConverter implements AttributeConverter<ZonedDateTime, Timestamp> {

    @Override
    public java.sql.Timestamp convertToDatabaseColumn(ZonedDateTime zonedDateTime) {
       return Optional.ofNullable(zonedDateTime)
    		   .map(ZonedDateTime::toInstant)
    		   .map(Timestamp::from)
    		   .orElse(null);
    }

    @Override
    public ZonedDateTime convertToEntityAttribute(Timestamp timestamp) {
    	return Optional.ofNullable(timestamp)
    			.map(Timestamp::toLocalDateTime)
    			.map(ldt -> ldt.atZone(ZoneId.systemDefault()))
    			.orElse(null);
    }

}