package rest;

import classes.Kweet;
import classes.User;
import classes.restClasses.KweetRest;
import exceptions.KweetNotFoundException;
import exceptions.UserNotFoundException;
import interfaces.JWTTokenNeeded;
import services.JsonService;
import services.KwetterService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/kweet")
public class KweetKweetEndpoint
{

    @Inject
    private JsonService jsonService;

    @Inject
    private KwetterService kwetterService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getKweets(@QueryParam("limit") int limit)
    {
//        GenericEntity<List<Kweet>> kweets = new GenericEntity<List<Kweet>>(kwetterService.getKweets()) {};

        return Response.ok(jsonService.parse(kwetterService.getKweets()), MediaType.APPLICATION_JSON_TYPE)
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
            kweet = kwetterService.getKweet(kweetId);
        }
        catch (KweetNotFoundException e)
        {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("Kweet doesn't exists")
                           .type(MediaType.TEXT_HTML)
                           .build();
        }

        return Response.ok(jsonService.parse(kweet), MediaType.APPLICATION_JSON_TYPE)
                       .build();
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_HTML})
    @Consumes(MediaType.APPLICATION_JSON)
    @JWTTokenNeeded
    public Response addKweet(KweetRest kweet)
    {
        // TODO create

        Kweet newKweet;
        try
        {
            newKweet = kwetterService.addKweet(kweet.getMessage(), kweet.getUsername());
        }
        catch (UserNotFoundException e)
        {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("User doesn't exists")
                           .type(MediaType.TEXT_HTML)
                           .build();
        }

        return Response.ok(jsonService.parse(newKweet), MediaType.APPLICATION_JSON_TYPE)
                       .build();
    }

    @DELETE
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_HTML})
    @Path("/{id}")
    public Response deleteKweet(@PathParam("id") long kweetId)
    {
        try
        {
            kwetterService.deleteKweet(kweetId);
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
        try
        {
            return Response.ok(kwetterService.getTends())
                           .build();
        }
        catch (Exception e)
        {
            return Response.ok(new ArrayList<String>())
                           .build();
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_HTML})
    @Path("/search")
    public Response getSearch(
            @QueryParam("search") String search,
            @DefaultValue("5") @QueryParam("limit") int limit)
    {
        return Response.ok(kwetterService.searchKweets(search))
                       .build();
    }

    @POST
    @Path("/{id}/like")
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_HTML})
    @Consumes(MediaType.APPLICATION_JSON)
    public Response likeKweet(
            @PathParam("id") long id,
            User user)
    {
        if (user == null)
        {
            return Response.notAcceptable(null)
                           .build();
        }

        try
        {
            if (kwetterService.getKweet(id)
                              .like(user))
            {
                return Response.status(Response.Status.NOT_MODIFIED)
                               .entity("user already liked kweet")
                               .type(MediaType.TEXT_HTML)
                               .build();
            }
        }
        catch (KweetNotFoundException e)
        {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("kweet doesn't exists")
                           .type(MediaType.TEXT_HTML)
                           .build();
        }

        return Response.ok("like")
                       .build();
    }
}
