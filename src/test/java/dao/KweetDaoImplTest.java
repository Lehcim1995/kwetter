package dao;

import classes.Kweet;
import interfaces.KweetDao;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.fail;

@RunWith(Parameterized.class)
public class KweetDaoImplTest //https://moepad.wordpress.com/tutorials/testing-multiple-interface-implementations-w-junit-4/
{

    KweetDao kweetDao;

    public KweetDaoImplTest(KweetDao kweetDao, String name){
        this.kweetDao = kweetDao;
    }

    // This makes this junit impl run multiple times but with different implementations
    @Parameterized.Parameters(name= "{1}")
    public static Collection<Object[]> getParameters() {
        return Arrays.asList(new Object[][] {
                { new KweetDaoCollection() , "Collection" },
                { new KweetDaoDatabase() , "Database" }
        });
    }

    String user1 = "User1";
    String user2 = "User2";
    String user3 = "User3";

    String trend1 = "Trend1";
    String trend2 = "Trend2";
    String trend3 = "Trend3";

    int totalKweets = 15;

    int expectedMentionsUser1 = 0;
    int expectedMentionsUser2 = 0;
    int expectedMentionsUser3 = 0;

    int expectedTrendsForTrend1 = 0;
    int expectedTrendsForTrend2 = 0;
    int expectedTrendsForTrend3 = 0;

    @Before
    public void setUp() throws Exception
    {
        if (kweetDao instanceof KweetDaoCollection) {
            ((KweetDaoCollection) kweetDao).init();
        }
        else
        {
//            ((KweetDaoDatabase) kweetDao).init();
        }

        // TODO add 30 or so kweets, and save the exspected results to that

        kweetDao.addKweet("01", user1);
        kweetDao.addKweet("02 @" + user2 + "", user1);
        kweetDao.addKweet("03 @" + user3 + "", user1);
        kweetDao.addKweet("04 @" + user2 + "@" + user3 + "", user1);
        kweetDao.addKweet("05", user1);

        kweetDao.addKweet("01", user2);
        kweetDao.addKweet("02 @" + user1 + "", user2);
        kweetDao.addKweet("03 @" + user3 + "", user2);
        kweetDao.addKweet("04 @" + user1 + "@" + user3 + "", user2);
        kweetDao.addKweet("05", user2);

        kweetDao.addKweet("01", user3);
        kweetDao.addKweet("02 @" + user1 + "", user3);
        kweetDao.addKweet("03 @" + user2 + "", user3);
        kweetDao.addKweet("04 @" + user1 + "@" + user2 + "", user3);
        kweetDao.addKweet("05", user3);
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
        String username = "Hans";

        int expected = 4;

        kweetDao.addKweet("01", username);
        kweetDao.addKweet("02", username);
        kweetDao.addKweet("03", username);
        kweetDao.addKweet("04", username);
        kweetDao.addKweet("05", username);
        kweetDao.addKweet("06 @" + username);
        kweetDao.addKweet("07 @" + username);
        kweetDao.addKweet("08 @" + username);
        kweetDao.addKweet("09");
        kweetDao.addKweet("10 @" + username);
        kweetDao.addKweet("11");

        List<Kweet> output = kweetDao.getKweetsFromMention(username);

        Assert.assertEquals(expected, output.size());

        for (Kweet k : output)
        {
            Assert.assertTrue(k.getMentions().contains(username));
        }
    }

    @Test
    public void getKweetsFromMention1()
    {
        fail();
    }

    @Test
    public void getKweetsFromTrend()
    {
        fail();
    }

    @Test
    public void getKweetsFromTrend1()
    {
        fail();
    }

    @Test
    public void addKweet()
    {


        fail();
    }

    @Test
    public void deleteKweet()
    {
        fail();
    }

    @Test
    public void getKweetsForUserProfile()
    {
        fail();
    }

    @Test
    public void getKweetsWithSQL()
    {
        fail();
    }

    @Test
    public void getKweet()
    {
        fail();
    }

    @Test
    public void getMentions()
    {
        fail();
    }

    @Test
    public void getTends()
    {
        fail();
    }
}