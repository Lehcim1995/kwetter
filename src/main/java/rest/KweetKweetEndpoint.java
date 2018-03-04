package rest;

import classes.Kweet;
import exceptions.KweetNotFoundException;
import services.KweetService;

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
    private KweetService kweetService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getKweets()
    {
        GenericEntity<List<Kweet>> kweets = new GenericEntity<List<Kweet>>(kweetService.getKweets()) {};

        return Response.ok(kweets).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getKweet(@PathParam("id") long kweetId)
    {
        return Response.ok(kweetService.getKweet(kweetId)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addKweet()
    {
        // TODO create

        //
        kweetService.addKweet("test", "test");

        return Response.noContent().build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response deleteKweet(@PathParam("id") long kweetId)
    {
        try
        {
            kweetService.deleteKweet(kweetId);
        }
        catch (KweetNotFoundException e)
        {
            e.printStackTrace();
        }

        return Response.noContent().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/trends")
    public Response getTrends()
    {
        return Response.ok(kweetService.getTends()).build();
    }
}
