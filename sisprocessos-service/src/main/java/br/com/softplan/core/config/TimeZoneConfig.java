package br.com.softplan.core.config;

import br.com.softplan.core.logger.HasLogging;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Locale;
import java.util.TimeZone;

@Configuration
public class TimeZoneConfig implements HasLogging {

    private static final Locale DEFAULT_LOCALE = new Locale("pt", "BR");
    private static final TimeZone DEFAULT_TIMEZONE = TimeZone.getTimeZone("America/Sao_Paulo");

    @PostConstruct
    public void setDefaultTimeZone() {
        TimeZone.setDefault(DEFAULT_TIMEZONE);
        Locale.setDefault(DEFAULT_LOCALE);
        getLogger().info("---------- CONFIG TIMEZONE ----------");
        getLogger().info("O aplicativo está rodando utilizando o Time Zone: {}", TimeZone.getDefault().getDisplayName());
        getLogger().info("O aplicativo está rodando utilizando o Locale: {}", Locale.getDefault().getDisplayName());
        getLogger().info("-------------------------------------");
    }

}
