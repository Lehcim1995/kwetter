package dao;

import classes.Kweet;
import interfaces.KweetDao;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class KweetDaoCollectionTest
{

    KweetDao kweetDao = new KweetDaoCollection();

    @Before
    public void setUp() throws Exception
    {
        ((KweetDaoCollection) kweetDao).init();
    }

    @After
    public void tearDown() throws Exception
    {

    }

    @Test
    public void getKweets()
    {
        kweetDao.addKweet("01");
        kweetDao.addKweet("02");
        kweetDao.addKweet("03");
        kweetDao.addKweet("04");
        kweetDao.addKweet("05");
        kweetDao.addKweet("06");
        kweetDao.addKweet("07");
        kweetDao.addKweet("08");
        kweetDao.addKweet("09");
        kweetDao.addKweet("10");
        kweetDao.addKweet("11");

        List<Kweet> k = kweetDao.getKweets();
        Assert.assertEquals(11, k.size());
    }

    @Test
    public void getKweetsFromUser()
    {
        String username = "Hans";

        int expected = 5;

        kweetDao.addKweet("01", username);
        kweetDao.addKweet("02", username);
        kweetDao.addKweet("03", username);
        kweetDao.addKweet("04", username);
        kweetDao.addKweet("05", username);
        kweetDao.addKweet("06");
        kweetDao.addKweet("07");
        kweetDao.addKweet("08");
        kweetDao.addKweet("09");
        kweetDao.addKweet("10");
        kweetDao.addKweet("11");

        List<Kweet> output = kweetDao.getKweetsFromUser(username);

        Assert.assertEquals(expected, output.size());

        for (Kweet k : output)
        {
            Assert.assertEquals(username, k.getOwner());
        }
    }

    @Test
    public void getKweetsFromUser1()
    {
        String username = "Hans";

        int expected = 3;

        kweetDao.addKweet("01", username);
        kweetDao.addKweet("02", username);
        kweetDao.addKweet("03", username);
        kweetDao.addKweet("04", username);
        kweetDao.addKweet("05", username);
        kweetDao.addKweet("06");
        kweetDao.addKweet("07");
        kweetDao.addKweet("08");
        kweetDao.addKweet("09");
        kweetDao.addKweet("10");
        kweetDao.addKweet("11");

        List<Kweet> output = kweetDao.getKweetsFromUser(username, expected);

        Assert.assertEquals(expected, output.size());

        for (Kweet k : output)
        {
            Assert.assertEquals(username, k.getOwner());
        }
    }

    @Test
    public void getKweetsFromMention()
    {
        //fail();
    }

    @Test
    public void getKweetsFromMention1()
    {
        //fail();
    }

    @Test
    public void getKweetsFromTrend()
    {
        //fail();
    }

    @Test
    public void getKweetsFromTrend1()
    {
        //fail();
    }

    @Test
    public void addKweet()
    {
        //fail();
    }

    @Test
    public void deleteKweet()
    {
        //fail();
    }

    @Test
    public void getKweetsForUserProfile()
    {
        //fail();
    }

    @Test
    public void getKweetsWithSQL()
    {
        //fail();
    }

    @Test
    public void getKweet()
    {
        //fail();
    }

    @Test
    public void getMentions()
    {
        //fail();
    }

    @Test
    public void getTends()
    {
        //fail();
    }
}