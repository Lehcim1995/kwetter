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
    public Response getKweets(@QueryParam("limit") int limit)
    {
        GenericEntity<List<Kweet>> kweets = new GenericEntity<List<Kweet>>(kweetService.getKweets()) {};

        return Response.ok(kweets).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getKweet(@PathParam("id") long kweetId)
    {
        Kweet kweet;
        try
        {
            kweet = kweetService.getKweet(kweetId);
        }
        catch (KweetNotFoundException e)
        {
            return Response.noContent().build();
        }


        return Response.ok(kweet).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addKweet(Kweet kweet)
    {
        // TODO create
        String message = kweet.getMessage();
        String user = kweet.getOwner();

        Kweet newKweet = kweetService.addKweet(message, user);

        return Response.ok(newKweet).build();
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

        return Response.ok("Kweet has been deleted").build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/trends")
    public Response getTrends()
    {

        return Response.ok(kweetService.getTends()).build();
    }
}
