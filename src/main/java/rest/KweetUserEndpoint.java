package rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/user")
public class KweetUserEndpoint
{
    @GET
    @Produces()
    public Response test()
    {
        return Response.ok("user").build();
    }
}
