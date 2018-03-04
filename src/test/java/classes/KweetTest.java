package classes;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

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
    public void getMentionsFromMessage()
    {
        String input = "@Jan, @Femke. @Werkt# @aan@elkaar @1234";

        List<String> expected = Arrays.asList("@Jan", "@Femke", "@Werkt", "@aan", "@elkaar", "@1234");

        List<String> output = Kweet.getMentionsFromMessage(input);

        Assert.assertEquals(expected.size(), output.size());
        Assert.assertEquals(expected, output);

    }

    @Test
    public void getTrendsFromMessage()
    {
        String input = "#Jan, #Femke. #Werkt@ #aan#elkaar #1234";

        List<String> expected = Arrays.asList("#Jan", "#Femke", "#Werkt", "#aan", "#elkaar", "#1234");

        List<String> output = Kweet.getTrendsFromMessage(input);

        Assert.assertEquals(expected.size(), output.size());
        Assert.assertEquals(expected, output);
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