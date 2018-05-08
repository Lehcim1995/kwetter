package rest;

import classes.Group;
import classes.User;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

public class KweetUserEndpointTest
{

    Client client;
    WebTarget root;
    static final String PATH = "/kwetter/rest/user/";
    static final String BASEURL = "http://localhost:8080" + PATH;
    String mediaType = MediaType.APPLICATION_JSON;

    public KweetUserEndpointTest() {
    }

    @Before
    public void setUp() {
        this.client = ClientBuilder.newClient();
        this.root = this.client.target(BASEURL);
    }

    @Test
    public void crud() {

        User u = new User("hans", new Group("lel"));

        User kweetResult = root.request().post(Entity.entity(u, mediaType), User.class);

        Collection<User> users = this.root.request().get(new GenericType<Collection<User>>() {});
        // TODO doe wat met users?

    }

    @Test
    public void crudResponse() {

    }
}