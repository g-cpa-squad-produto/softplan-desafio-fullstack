package br.com.damilopaixao.ws.core;

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
	
	// TXT Fixed Length File
	public static final DateTimeFormatter TXT_DATE_FORMAT = DateTimeFormatter.ofPattern("ddMMyyyy");
	public static final DateTimeFormatter TXT_TIME_FORMAT = DateTimeFormatter.ofPattern("HHmm");
	public static final DateTimeFormatter TXT_DATETIME_FORMAT = DateTimeFormatter.ofPattern("ddMMyyyyHHmm");

	public static final Function<BigDecimal, String> mapBigDecimalToFixedLengthCurrency = 
			valor -> String.valueOf(valor.multiply(BIG_DECIMAL_100).toBigInteger());

	// XML File
	public static final DateTimeFormatter XML_DATETIME_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

	public static final Function<String, Long> mapXmlCodigoToLong = codigo ->
		Optional.ofNullable(codigo)
			.filter(StringUtils::isNotBlank)
			.map(cod -> StringUtils.stripStart(cod, "0"))
			.map(cod -> StringUtils.removeAll(cod, "\\-"))
			.map(cod -> StringUtils.removeAll(cod, "\\."))
			.filter(StringUtils::isNotBlank)
			.map(mapStringToLong)
			.orElse(null);
		
		public static final Function<String, BigDecimal> mapXmlCodigoToBigDecimal = codigo ->
		Optional.ofNullable(codigo)
			.filter(StringUtils::isNotBlank)
			.map(cod -> StringUtils.stripStart(cod, "0"))
			.map(cod -> StringUtils.removeAll(cod, "\\-"))
			.map(cod -> StringUtils.removeAll(cod, "\\."))
			.filter(StringUtils::isNotBlank)
			.map(mapStringToBigDecimal
					)
			.orElse(null);
	
	public static final Function<String, BigDecimal> mapXmlCurrencyToBigDecimal = s -> 
		Optional.ofNullable(s)
			.map(value -> value.replaceAll(",", "."))
			.map(BigDecimal::new)
			.orElse(null);
		
	public UtilFunctions() {
		throw new AssertionError();
	}

}
