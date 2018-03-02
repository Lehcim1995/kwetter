package rest;

import classes.Kweet;
import dao.KweetDaoCollection;
import interfaces.KweetDao;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/kweet")
public class KweetKweetEndpoint
{
    @Inject
    @Alternative
    private KweetDao kweetDao;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getKweets()
    {
        GenericEntity<List<Kweet>> kweets = new GenericEntity<List<Kweet>>(kweetDao.getKweets()){};

        return Response.ok(kweets).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getKweet(@PathParam("id") long kweetId)
    {
        kweetDao.getKweet(kweetId);
        return Response.status(Response.Status.NOT_IMPLEMENTED).build();
    }
}
