package rest;

import classes.Kweet;
import classes.User;
import exceptions.KweetNotFoundException;
import exceptions.UserNotFoundException;
import services.KweetService;
import services.UserService;

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

    @Inject
    private UserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getKweets(@QueryParam("limit") int limit)
    {
        GenericEntity<List<Kweet>> kweets = new GenericEntity<List<Kweet>>(kweetService.getKweets()) {};

        return Response.ok(kweets)
                       .build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_HTML})
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
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("Kweet doesn't exists")
                           .type(MediaType.TEXT_HTML)
                           .build();
        }

        return Response.ok(kweet)
                       .build();
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_HTML})
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addKweet(Kweet kweet)
    {
        // TODO create
        String message = kweet.getMessage();
        User user = null;
        try
        {
            user = userService.getUser(kweet.getOwnerName());
        }
        catch (UserNotFoundException e)
        {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("User doesn't exist")
                           .type(MediaType.TEXT_HTML)
                           .build();
        }


        Kweet newKweet = kweetService.addKweet(message, user);

        return Response.ok(newKweet)
                       .build();
    }

    @DELETE
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_HTML})
    @Path("/{id}")
    public Response deleteKweet(@PathParam("id") long kweetId)
    {
        try
        {
            kweetService.deleteKweet(kweetId);
        }
        catch (KweetNotFoundException e)
        {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("kweet doesn't exists")
                           .type(MediaType.TEXT_HTML)
                           .build();
        }

        return Response.ok("Kweet has been deleted")
                       .build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_HTML})
    @Path("/trends")
    public Response getTrends(@DefaultValue("5") @QueryParam("limit") int limit)
    {
        return Response.ok(kweetService.getTends(limit))
                       .build();
    }
}
