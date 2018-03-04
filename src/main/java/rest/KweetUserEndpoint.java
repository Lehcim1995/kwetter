package rest;

import interfaces.KweetDao;
import interfaces.UserDao;
import services.KweetService;
import services.UserService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
        return Response.ok("user").build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response getUser(@PathParam("id") String username)
    {
        return Response.ok("user").build();
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
        kweetService.getKweetsFromUser(username);
        return Response.ok("mentions").build();
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
