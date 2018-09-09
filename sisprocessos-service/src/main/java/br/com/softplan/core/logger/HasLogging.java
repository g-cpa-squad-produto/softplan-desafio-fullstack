package br.com.softplan.core.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Interface utilizada em classes que possuem a necessidade de salvar mensagens no arquivo de log
 *
 * @author Samuel Correia Guimarães
 */
public interface HasLogging {

    default Logger getLogger() {
        return LoggerFactory.getLogger(getClass());
    }

}