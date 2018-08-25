package br.com.danilopaixao.ws.core;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;
import java.util.function.Function;

import org.apache.commons.lang3.StringUtils;

public final class UtilFunctions {

	private static final BigDecimal BIG_DECIMAL_100 = new BigDecimal("100");

	// Utils
	public static final Function<String, Long> mapStringToLong = s ->
		Optional.ofNullable(s)
			.map(BigDecimal::new)
			.map(b -> b.longValue())
			.orElse(null);
		
	// Utils
	public static final Function<String, BigDecimal> mapStringToBigDecimal = s ->
		Optional.ofNullable(s)
			.map(BigDecimal::new)
			.orElse(null);
		
	public static final Function<Date, LocalDateTime> mapDateToLocalDateTime =
			// @formatter:off
			date -> Optional.ofNullable(date)
						.map(Date::getTime)
						.map(Instant::ofEpochMilli)
						.map(instant -> instant.atZone(ZoneId.systemDefault()))
						.map(ZonedDateTime::toLocalDateTime)
						.orElse(null);
			// @formatter:on
		
	public static final <I> String transformToString(I value, String orElse) {
		return Optional.ofNullable(value)
				.map(String::valueOf)
				.orElse(orElse);
	}
	
	public static final <I,O> O transformTo(final I value, final O orElse, final Function<I, O> transformer) {
		return Optional.ofNullable(value)
				.map(transformer)
				.orElse(orElse);
	}
	
	public static final <I> String transformToString(final I value, final String orElse, final Function<I, String> transformer) {
		return Optional.ofNullable(value)
				.map(transformer)
				.map(String::valueOf)
				.orElse(orElse);
	}
	
	public static final LocalDate localDateParser(final String value, String patternOf) {
		return LocalDate.parse(value, DateTimeFormatter.ofPattern(patternOf));
	}
	
	public static final Function<BigDecimal, String> mapBigDecimalToFixedLengthCurrency = 
			valor -> String.valueOf(valor.multiply(BIG_DECIMAL_100).toBigInteger());
		
	public UtilFunctions() {
		throw new AssertionError();
	}

}
