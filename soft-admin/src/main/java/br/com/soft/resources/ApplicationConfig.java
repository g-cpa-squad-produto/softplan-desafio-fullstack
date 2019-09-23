package br.com.soft.resources;

import io.swagger.jaxrs.config.BeanConfig;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Moquiuti
 */
@ApplicationPath("v1")
public class ApplicationConfig extends Application {

    private final Logger log = LogManager.getLogger(getClass());

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        try {
            Class jsonProvider = Class.forName("org.glassfish.jersey.jackson.JacksonFeature");
            resources.add(jsonProvider);
        } catch (ClassNotFoundException ex) {
            log.trace("Erro ao adicionar o provider do Json", ex);
        }
        addRestResourceClasses(resources);
        addMyRestResourceClasses(resources);
        return resources;
    }

    public ApplicationConfig() {
        BeanConfig config = new BeanConfig();
        config.setTitle("Soft Plan - Admin");
        config.setDescription("");
        config.setVersion("1.0-BETA");
        config.setBasePath("/soft-admin/v1");
        config.setSchemes(new String[]{"http"});
        config.setResourcePackage("br.com.soft.resources");
        config.setScan(true);
    }

    private void addMyRestResourceClasses(Set<Class<?>> resources) {
        resources.add(io.swagger.jaxrs.listing.ApiListingResource.class);
        resources.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);

    }

    /**
     * Do not modify addRestResourceClasses() method. It is automatically
     * populated with all resources defined in the project. If required, comment
     * out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(br.com.soft.resources.ProcessoResource.class);
        resources.add(br.com.soft.resources.UsuarioResource.class);
    }

}
