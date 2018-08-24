package br.com.danilopaixao.ws.core;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Optional;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalTimeConverter implements AttributeConverter<LocalTime, Time> {

    @Override
    public java.sql.Time convertToDatabaseColumn(LocalTime localTime) {
    	return Optional.ofNullable(localTime)
    		   .map(Time::valueOf)
    		   .orElse(null);
    }

    @Override
    public LocalTime convertToEntityAttribute(Time time) {
    	return Optional.ofNullable(time)
    			.map(Time::toLocalTime)
    			.orElse(null);
    }

}