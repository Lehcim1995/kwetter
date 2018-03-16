package dao;

import classes.Kweet;
import classes.User;
import exceptions.KweetNotFoundException;
import interfaces.KweetDao;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static interfaces.KweetDao.MENTION_TOKEN;
import static interfaces.KweetDao.TREND_TOKEN;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@RunWith(Parameterized.class)
public class KweetDaoImplTest //https://moepad.wordpress.com/tutorials/testing-multiple-interface-implementations-w-junit-4/
{

    KweetDao kweetDao;

    public KweetDaoImplTest(KweetDao kweetDao, String name){
        this.kweetDao = kweetDao;
    }

    // This makes this junit impl run multiple times but with different implementations
    // Very spicy
    @Parameterized.Parameters(name= "{1}")
    public static Collection<Object[]> getParameters() {
        return Arrays.asList(new Object[][] {
                { new KweetDaoCollection() , "Collection" },
                { new KweetDaoDatabase() , "Database" }
        });
    }

    User user1 = new User("user1");
    User user2 = new User("user2");
    User user3 = new User("user3");

    String trend1 = "Trend1";
    String trend2 = "Trend2";
    String trend3 = "Trend3";

    int totalKweets = 15;

    int expectedKweetForUser1 = 5;
    int expectedKweetForUser2 = 5;
    int expectedKweetForUser3 = 5;

    int totalTrends = 2;

    int expectedMentionsUser1 = 4;
    int expectedMentionsUser2 = 4;
    int expectedMentionsUser3 = 4;

    int expectedTrendsForTrend1 = 3;
    int expectedTrendsForTrend2 = 3;

    @Before
    public void setUp() throws Exception
    {
        if (kweetDao instanceof KweetDaoCollection) {
            ((KweetDaoCollection) kweetDao).init();
        }
        else
        {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("testPU");
            EntityManager em = emf.createEntityManager();
            ((KweetDaoDatabase) kweetDao).setEntityManager(em);
        }

        // TODO add 30 or so kweets, and save the exspected results to that

        kweetDao.addKweet("01", user1);
        kweetDao.addKweet("02 @" + user2 + " #Trend2", user1);
        kweetDao.addKweet("03 @" + user3 + "", user1);
        kweetDao.addKweet("04 @" + user2 + "@" + user3 + "", user1);
        kweetDao.addKweet("05 #Trend1", user1);

        kweetDao.addKweet("01", user2);
        kweetDao.addKweet("02 @" + user1 + " #Trend2", user2);
        kweetDao.addKweet("03 @" + user3 + "", user2);
        kweetDao.addKweet("04 @" + user1 + "@" + user3 + "", user2);
        kweetDao.addKweet("05 #Trend1", user2);

        kweetDao.addKweet("01", user3);
        kweetDao.addKweet("02 @" + user1 + " #Trend2", user3);
        kweetDao.addKweet("03 @" + user2 + "", user3);
        kweetDao.addKweet("04 @" + user1 + "@" + user2 + "", user3);
        kweetDao.addKweet("05 #Trend1", user3);
    }

    @After
    public void tearDown() throws Exception
    {

    }

    @Test
    public void getKweets()
    {
        List<Kweet> k = kweetDao.getKweets();
        Assert.assertEquals(totalKweets, k.size());
    }

    @Test
    public void getKweets1()
    {
        List<Kweet> k = kweetDao.getKweets(); // TODO add amount parameter
        Assert.assertEquals(totalKweets, k.size());
    }

    @Test
    public void getKweetsFromUser()
    {
        List<Kweet> output = kweetDao.getKweetsFromUser(user1.getUsername());

        Assert.assertEquals(expectedKweetForUser1, output.size());

        for (Kweet k : output)
        {
            Assert.assertEquals(user1, k.getOwner());
        }
    }

    @Test
    public void getKweetsFromUser1()
    {

        int expectedLimit = 3;

        List<Kweet> output = kweetDao.getKweetsFromUser(user1.getUsername(), expectedLimit);

        Assert.assertEquals(expectedLimit, output.size());

        for (Kweet k : output)
        {
            Assert.assertEquals(user1, k.getOwner());
        }
    }

    @Test
    public void getKweetsFromMention()
    {
        List<Kweet> output = kweetDao.getKweetsFromMention(user1.getUsername());

        Assert.assertEquals(expectedMentionsUser1, output.size());

        for (Kweet k : output)
        {
            Assert.assertTrue(k.getMentions().contains(MENTION_TOKEN + user1.getUsername()));
        }
    }

    @Test
    public void getKweetsFromMention1()
    {
        int limit = 2;

        List<Kweet> output = kweetDao.getKweetsFromMention(user1.getUsername(), limit);

        Assert.assertEquals(limit, output.size());

        for (Kweet k : output)
        {
            Assert.assertTrue(k.getMentions().contains(MENTION_TOKEN + user1.getUsername()));
        }
    }

    @Test
    public void getKweetsFromTrend()
    {
        List<Kweet> output = kweetDao.getKweetsFromTrend(trend1);

        Assert.assertEquals(expectedTrendsForTrend1, output.size());

        for (Kweet k : output)
        {
            Assert.assertTrue(k.getTrends().contains(TREND_TOKEN + trend1));
        }
    }

    @Test
    public void getKweetsFromTrend1()
    {
        int limit = 3;

        List<Kweet> output = kweetDao.getKweetsFromTrend(trend1, limit);

        Assert.assertEquals(limit, output.size());

        for (Kweet k : output)
        {
            Assert.assertTrue(k.getTrends().contains(TREND_TOKEN + trend1));
        }
    }

    @Test
    public void addKweet()
    {
        int extraKweets = 5;
//        kweetDao.addKweet("1", user1);
//        kweetDao.addKweet("2", user1);
//        kweetDao.addKweet("3", user1);
//        kweetDao.addKweet("4", user1);
//        kweetDao.addKweet("5", user1);


        List<Kweet> k = kweetDao.getKweets();
        Assert.assertEquals(totalKweets + extraKweets, k.size());
    }

    @Test
    public void deleteKweet()
    {

        int deletedId = 5;

        try
        {
            kweetDao.deleteKweet(deletedId);
        }
        catch (KweetNotFoundException e)
        {
            fail();
        }

        try
        {
            kweetDao.getKweet(deletedId);
        }
        catch (KweetNotFoundException e)
        {
            assertTrue(true);
        }
    }

    @Test
    public void getKweet()
    {
        // 15 kweets

        for (int i = 1; i <= 15; i++)
        {
            try
            {
                kweetDao.getKweet(i);
            }
            catch (KweetNotFoundException e)
            {
                fail();
            }
        }
    }

    @Test
    public void getTends()
    {
        // 2 trends
        List<String> output = kweetDao.getTends();

        Assert.assertEquals(2, output.size());
    }
}