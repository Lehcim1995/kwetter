package rest;

import classes.Hateos;
import classes.Kweet;
import classes.User;

import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;
import java.util.Vector;

@Priority(2)
public class HateosFilter implements ContainerResponseFilter
{

    @Context
    UriInfo uriInfo;

    private final String base = "kwetter/rest/";
    private final String kweet = "kweet/";
    private final String user = "user/";


    @Override
    public void filter(
            ContainerRequestContext requestContext,
            ContainerResponseContext responseContext) throws IOException
    {
        System.out.println("hateos Filter");

        if (responseContext.getEntity().getClass() == Vector.class)
        {
            Vector<? extends Hateos> objects = (Vector<? extends Hateos>) responseContext.getEntity();

            objects.forEach(o -> {

                if (o instanceof Kweet)
                {
                    updateHateos(((Kweet) o).getOwner());
                }

                if (o instanceof  User)
                {
                   ((User) o).getKweets().forEach( kweet1 -> updateHateos(kweet1));
                }

                updateHateos(o);
            });
        }


        if (responseContext.getEntity() instanceof Hateos)
        {
            Hateos h = (Hateos) responseContext.getEntity();

            updateHateos(h);
        }
    }

    private void updateHateos(Hateos hateos)
    {

        final String url = "localhost:8080/";

        String service = "";
        String id = "";

        if (hateos instanceof Kweet)
        {
            service = kweet;
            id = ((Kweet) hateos).getId() + "";
        }
        else if (hateos instanceof User)
        {
            service = user;
            id = ((User) hateos).getUsername();
        }
        else
        {
            return;
        }

        Link self = Link.fromUri(url +base + service + id).rel("self").type("GET").build();
        Link delete = Link.fromUri(url + base + service + id).rel("delete").type("DELETE").build(); //Link.fromUri(uriInfo.getAbsolutePath()).rel("delete").type("DELETE").build();
        Link update = Link.fromUri(url + base + service + id).rel("update").type("POST").build(); //Link.fromUri(uriInfo.getAbsolutePath()).rel("update").type("POST").build();

        hateos.setSelf(self);
        hateos.setDelete(delete);
        hateos.setUpdate(update);
    }
}
