package rest;

import classes.Kweet;
import classes.User;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class KweetKweetEndpointTest
{

    Client client;
    WebTarget root;
    static final String PATH = "/kwetter/rest/kweet/";
    static final String BASEURL = "http://localhost:8080" + PATH;

    public KweetKweetEndpointTest() {
    }

    @Before
    public void setUp() {
        this.client = ClientBuilder.newClient();
        this.root = this.client.target(BASEURL);
    }

    @Test
    public void crud() { //rest assured.
        String mediaType = MediaType.APPLICATION_JSON;
        User u = new User("user1");
        Kweet kweet = new Kweet("message", u);

        final Entity<Kweet> entity = Entity.entity(kweet, mediaType);
        Kweet kweetResult = this.root.request().post(entity, Kweet.class);
        assertThat(kweetResult, is(kweet));

        Kweet result = this.root.path(String.valueOf(kweet.getId())).request(mediaType).get(Kweet.class);
        assertThat(result, is(kweet));


        Collection<Kweet> kweets = this.root.request().get(new GenericType<Collection<Kweet>>() {});
        // startup: 6 persons
        assertThat(kweets.size(), is(1));
        assertThat(kweets, hasItem(kweet));


        this.root.path(String.valueOf(kweet.getId())).request(mediaType).delete(Kweet.class);

        kweets = this.root.request().get(new GenericType<Collection<Kweet>>() {});

        assertThat(kweets.size(), is(1));

    }

    @Test
    public void crudResponse() {
        User u = new User("user1");
        Kweet k = new Kweet("hi", u);

        // add
        final String mediaType = MediaType.APPLICATION_JSON;
        final Entity<Kweet> entity = Entity.entity(k, mediaType);
        Response response = this.root.request().post(entity, Response.class);

        Kweet readEntity = response.readEntity(Kweet.class);
        assertThat(response.getStatus(), is(200));
        assertThat(readEntity, is(k));

        //get

//        response = this.root.path(String.valueOf(k.getId())).request(mediaType).delete(Response.class);
//        assertThat(response.getStatus(), is(204));
    }
}