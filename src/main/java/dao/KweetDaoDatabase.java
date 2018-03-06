package dao;

import classes.Kweet;
import interfaces.KweetDao;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
@Alternative
public class KweetDaoDatabase implements KweetDao
{

    private EntityManager entityManager;

    @Inject // Nicer way of doing it ;)
    public void setEntityManager(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }

    @Override
    public List<Kweet> getKweets()
    {
        return null;
    }

    @Override
    public List<Kweet> getKweetsFromUser(String username)
    {
        return null;
    }

    @Override
    public List<Kweet> getKweetsFromUser(
            String username,
            int amount)
    {
        return null;
    }

    @Override
    public List<Kweet> getKweetsFromMention(String mention)
    {
        return null;
    }

    @Override
    public List<Kweet> getKweetsFromMention(
            String mention,
            int amount)
    {
        return null;
    }

    @Override
    public List<Kweet> getKweetsFromTrend(String trend)
    {
        return null;
    }

    @Override
    public List<Kweet> getKweetsFromTrend(
            String trend,
            int amount)
    {
        return null;
    }

    @Override
    public Kweet addKweet(String message)
    {
        return null;
    }

    @Override
    public Kweet addKweet(
            String message,
            String user)
    {
        return null;
    }

    @Override
    public boolean deleteKweet(long id)
    {
        return false;
    }

    @Override
    public List<Kweet> getKweetsForUserProfile(String userName)
    {
        return null;
    }

    @Override
    public List<Kweet> getKweetsWithSQL(String sql)
    {
        return null;
    }

    @Override
    public Kweet getKweet(long id)
    {
        return null;
    }

    @Override
    public List<String> getTends()
    {
        return null;
    }
}