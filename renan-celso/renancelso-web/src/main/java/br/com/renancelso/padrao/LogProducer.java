package br.com.renancelso.padrao;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.apache.log4j.Logger;

/**
 * @author Renan Celso
 * @version 1.0.0, 16/10/2014
 * @since 1.0.0
 */
public class LogProducer {

    /**
     * Informa ao container que todas as injecoes de dependencia da classe
     * {@link Logger} devem ser instanciadas a partir do metodo
     * Logger.getLogger() passando como parametro o nome da classe na qual foi
     * realizada a injecao do {@link Logger}.
     *
     * @param ip
     *            Ponto de injecao (parametro passado pelo container atraves do
     *            CDI)
     * @return Nova instancia de @{link Logger} para a classe na qual a variavel
     *         foi instanciada
     */
    @Produces
    public Logger createLogger(InjectionPoint ip) {
        return Logger.getLogger(ip.getMember().getDeclaringClass().getName());
    }
}