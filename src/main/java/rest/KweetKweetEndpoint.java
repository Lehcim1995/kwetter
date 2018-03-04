package rest;

import classes.Kweet;
import interfaces.KweetDao;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/kweet")
public class KweetKweetEndpoint
{
    @Inject
    private KweetDao kweetDao; //TODO create service

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getKweets()
    {
        GenericEntity<List<Kweet>> kweets = new GenericEntity<List<Kweet>>(kweetDao.getKweets()) {};

        return Response.ok(kweets).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getKweet(@PathParam("id") long kweetId)
    {
        return Response.ok(kweetDao.getKweet(kweetId)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addKweet()
    {
        // TODO create

        return Response.noContent().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/trends")
    public Response getTrends()
    {
        return Response.ok(kweetDao.getTends()).build();
    }
}
