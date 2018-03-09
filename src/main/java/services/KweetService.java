package services;

import classes.Kweet;
import exceptions.KweetNotFoundException;
import interfaces.KweetDao;

import javax.ejb.EJB;
import javax.enterprise.inject.Default;
import javax.inject.Singleton;
import java.util.List;

@Singleton
@Default
public class KweetService
{
    @EJB
    private KweetDao kweetDao;

    public void setKweetDao(KweetDao kweetDao)
    {
        this.kweetDao = kweetDao;
    }

    public List<Kweet> getKweets()
    {
        return kweetDao.getKweets();
    }

    public List<Kweet> getKweetsFromUser(String username)
    {
        return kweetDao.getKweetsFromUser(username);
    }

    public List<Kweet> getKweetsFromUser(
            String username,
            int amount)
    {
        return kweetDao.getKweetsFromUser(username, amount);
    }

    public List<Kweet> getKweetsFromMention(String mention)
    {
        return kweetDao.getKweetsFromMention(mention);
    }

    public List<Kweet> getKweetsFromMention(
            String mention,
            int amount)
    {
        return kweetDao.getKweetsFromMention(mention, amount);
    }

    public List<Kweet> getKweetsFromTrend(String trend)
    {
        return kweetDao.getKweetsFromTrend(trend);
    }

    public List<Kweet> getKweetsFromTrend(
            String trend,
            int amount)
    {
        return kweetDao.getKweetsFromTrend(trend, amount);
    }

    @Deprecated
    public Kweet addKweet(String message)
    {
        return kweetDao.addKweet(message);
    }

    public Kweet addKweet(
            String message,
            String user)
    {
        return kweetDao.addKweet(message, user);
    }

    public boolean deleteKweet(long id) throws KweetNotFoundException
    {
        return kweetDao.deleteKweet(id);
    }

    public List<Kweet> getKweetsForUserProfile(String userName)
    {
        return kweetDao.getKweetsForUserProfile(userName);
    }

    public List<Kweet> getKweetsWithSQL(String sql)
    {
        return kweetDao.getKweetsWithSQL(sql);
    }

    public Kweet getKweet(long id) throws KweetNotFoundException
    {
        return kweetDao.getKweet(id);
    }

    public List<String> getTends()
    {
        return kweetDao.getTends();
    }

    public List<Kweet> getKweets(int limit)
    {
        return kweetDao.getKweets(limit);
    }

    public List<String> getTends(int limit)
    {
        return kweetDao.getTends(limit);
    }
}
