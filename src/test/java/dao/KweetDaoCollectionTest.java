package dao;

import classes.Kweet;
import interfaces.KweetDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class KweetDaoCollectionTest
{

    KweetDao kweetDao = new KweetDaoCollection();

    @Before
    public void setUp() throws Exception
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
    }

    @After
    public void tearDown() throws Exception
    {

    }

    @Test
    public void getKweets()
    {
        //fail();
        List<Kweet> k = kweetDao.getKweets();
    }

    @Test
    public void getKweetsFromUser()
    {
        //fail();
    }

    @Test
    public void getKweetsFromUser1()
    {
        //fail();
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