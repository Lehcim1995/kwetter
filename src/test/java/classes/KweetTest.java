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
}