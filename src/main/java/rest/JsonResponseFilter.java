package rest;

import classes.Kweet;
import classes.User;
import services.JsonService;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

@Priority(1)
public class JsonResponseFilter implements ContainerResponseFilter
{
    @Inject
    private JsonService jsonService;

    @Override
    public void filter(
            ContainerRequestContext requestContext,
            ContainerResponseContext responseContext) throws IOException
    {
        System.out.println("Responce type");
        System.out.println(responseContext.getMediaType());

        //check if media type contains or is Json media type
        if (!responseContext.getMediaType()
                            .isCompatible(MediaType.valueOf(MediaType.APPLICATION_JSON)))
        {
            return;
        }


        System.out.println("Response class");
        System.out.println(responseContext.getEntity()
                                          .getClass()
                                          .toString());


        // check for classes to parse to json
        if (responseContext.getEntity()
                           .getClass() == User.class || responseContext.getEntity()
                                                                       .getClass() == Kweet.class || responseContext.getEntity()
                                                                                                                    .getClass() == List.class || responseContext.getEntity()
                                                                                                                                                                .getClass() == Vector.class)
        {
            String json = jsonService.parse(responseContext.getEntity());
            responseContext.setEntity(json);
            System.out.println("changed to Json");
        }


    }
}
