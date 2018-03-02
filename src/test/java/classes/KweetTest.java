package classes;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class KweetTest
{
    @Before
    public void setUp() throws Exception
    {

    }

    @After
    public void tearDown() throws Exception
    {

    }

    @Test
    public void test()
    {
        String text = "dit is echt walgelijk #vies #ikmoetslapen #opruimen @geert";

        List<String> trends = Kweet.getTrendsFromMessage(text);
        List<String> mensions = Kweet.getMentionsFromMessage(text);
    }

    @Test
    public void getMentionsFromMessage()
    {
    }

    @Test
    public void getTrendsFromMessage()
    {
    }

    @Test
    public void setId()
    {
    }

    @Test
    public void setMessage()
    {
    }

    @Test
    public void setOwner()
    {
    }

    @Test
    public void setPostDate()
    {
    }

    @Test
    public void getId()
    {
    }

    @Test
    public void getMentions()
    {
    }

    @Test
    public void getHarts()
    {
    }

    @Test
    public void getTrends()
    {
    }

    @Test
    public void getMessage()
    {
    }

    @Test
    public void getOwner()
    {
    }

    @Test
    public void getPostDate()
    {
    }
}