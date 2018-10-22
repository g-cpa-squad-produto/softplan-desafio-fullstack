package org.gradle;

import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
/**
 * 
 * @author Thiago
 *
 *	Esta classe deifine a porta utilizada pelo tomcat auto contido no spring boot
 *
 */
@Controller
public class ServletConfig {
    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return (container -> {
            container.setPort(9000);
        });
    }
}