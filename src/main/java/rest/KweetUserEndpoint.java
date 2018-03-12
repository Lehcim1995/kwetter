package rest;

import classes.Kweet;
import classes.User;
import exceptions.IdAlreadyExistsException;
import exceptions.NoPermissionException;
import exceptions.UserNotFoundException;
import services.KweetService;
import services.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/user")
public class KweetUserEndpoint // https://github.com/kongchen/swagger-maven-plugin/blob/master/README.md // TODO
{
    @Inject
    private UserService userService;

    @Inject
    private KweetService kweetService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers()
    {
        GenericEntity<List<User>> users = new GenericEntity<List<User>>(userService.getUsers()) {};

        return Response.ok(users)
                       .build();
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_HTML})
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(User user)
    {
        User newUser;

        if (user.getUsername()
                .isEmpty())
        {
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                           .entity("username is empty")
                           .type(MediaType.TEXT_HTML)
                           .build();
        }

        try
        {
            newUser = userService.createUser(user.getUsername(), "password");
        }
        catch (IdAlreadyExistsException e)
        {
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                           .entity("User already exists")
                           .type(MediaType.TEXT_HTML)
                           .build();
        }

        return Response.ok(newUser)
                       .build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_HTML})
    @Path("{id}")
    public Response getUser(@PathParam("id") String username)
    {
        User u;

        try
        {
            u = userService.getUser(username);
        }
        catch (UserNotFoundException e)
        {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("User not found")
                           .type(MediaType.TEXT_HTML)
                           .build();
        }

        return Response.ok(u)
                       .build();
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_HTML})
    @Path("{id}/addkweet")
    public Response addKweetToUser(
            @PathParam("id") String username,
            Kweet kweet)
    {
        String message = kweet.getMessage();
        User user = null;
        try
        {
            user = userService.getUser(username);
        }
        catch (UserNotFoundException e)
        {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("User not found")
                           .type(MediaType.TEXT_HTML)
                           .build();
        }

        Kweet newKweet = kweetService.addKweet(message, user);

        return Response.ok(newKweet)
                       .build();
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_HTML})
    @Path("{id}")
    public Response updateUser(@PathParam("id") String username)
    {
        User u;

        try
        {
            u = userService.updateUser(userService.getUser(username));
        }
        catch (UserNotFoundException e)
        {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("User not found")
                           .type(MediaType.TEXT_HTML)
                           .build();
        }
        catch (NoPermissionException e)
        {
            return Response.status(Response.Status.UNAUTHORIZED)
                           .entity("No permission")
                           .type(MediaType.TEXT_HTML)
                           .build();
        }

        return Response.ok(u)
                       .build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_HTML})
    @Path("{id}/kweets")
    public Response getKweetsFromUser(
            @PathParam("id") String username,
            @DefaultValue("5") @QueryParam("limit") int limit)
    {
        GenericEntity<List<Kweet>> userKweets = new GenericEntity<List<Kweet>>(kweetService.getKweetsFromUser(username)) {};

        return Response.ok(userKweets)
                       .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}/mentions")
    public Response getKweetsFromMentions(
            @PathParam("id") String username,
            @DefaultValue("5") @QueryParam("limit") int limit)
    {
        GenericEntity<List<Kweet>> mentions = new GenericEntity<List<Kweet>>(kweetService.getKweetsFromMention(username)) {};

        return Response.ok(mentions)
                       .build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_HTML})
    @Path("{id}/timeline")
    public Response getKweetsFromTimeline(
            @PathParam("id") String username,
            @DefaultValue("5") @QueryParam("limit") int limit)
    {
        kweetService.getKweetsFromUser(username);
        return Response.ok("timeline")
                       .type(MediaType.TEXT_HTML)
                       .build();
    }
}
