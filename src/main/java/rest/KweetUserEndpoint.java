package rest;

import classes.Kweet;
import classes.User;
import exceptions.IdAlreadyExistsException;
import exceptions.UserNotFoundException;
import interfaces.KweetDao;
import interfaces.UserDao;
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

        return Response.ok(users).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(User user)
    {
        User newUser;

        if (user.getUsername().isEmpty())
        {
            return Response.ok("username is empty").build();
        }

        try
        {
            newUser = userService.createUser(user.getUsername(), "password");
        }
        catch (IdAlreadyExistsException e)
        {
            return Response.noContent().build();
        }

        return Response.ok(newUser).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
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
            return Response.noContent().build();
        }

        return Response.ok(u).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}/kweets")
    public Response getKweetsFromUser(@PathParam("id") String username)
    {
        kweetService.getKweetsFromUser(username);
        return Response.ok("user").build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}/mentions")
    public Response getKweetsFromMentions(@PathParam("id") String username)
    {
        GenericEntity<List<Kweet>> mentions = new GenericEntity<List<Kweet>>(kweetService.getKweetsFromMention(username)) {};

        return Response.ok(mentions).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}/timeline")
    public Response getKweetsFromTimeline(@PathParam("id") String username)
    {
        kweetService.getKweetsFromUser(username);
        return Response.ok("timeline").build();
    }

}
