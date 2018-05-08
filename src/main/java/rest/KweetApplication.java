package rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/rest")
public class KweetApplication extends Application
{
//    public KweetApplication()
//    {
//        BeanConfig beanConfig = new BeanConfig();
//        beanConfig.setVersion("2.0.0");
//        beanConfig.setSchemes(new String[]{"http"});
//        beanConfig.setHost("localhost:8080");
//        beanConfig.setBasePath("/kwetter/rest");
//        beanConfig.setResourcePackage("rest");
//        beanConfig.setPrettyPrint(true);
//        beanConfig.setScan(true);
//    }

    @Override
    public Set<Class<?>> getClasses()
    {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(KweetKweetEndpoint.class);
        classes.add(KweetUserEndpoint.class);
        classes.add(CORSFilter.class);
        classes.add(io.swagger.jaxrs.listing.ApiListingResource.class);
        classes.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);

        return classes;
    }
}
