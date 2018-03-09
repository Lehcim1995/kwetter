package dao;

import classes.Kweet;
import interfaces.KweetDao;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.enterprise.inject.Default;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
@Default
@JPA
public class KweetDaoDatabase implements KweetDao
{
    @PersistenceContext(unitName = "kwetterPU")
    private EntityManager entityManager;

    // Nicer way of doing it ;)
    public void setEntityManager(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }

    @Override
    public List<Kweet> getKweets()
    {

        if (entityManager == null)
        {
            return Arrays.asList(new Kweet("entity", "is null"));
        }

        return entityManager.createNamedQuery("kweet.getKweets").getResultList();
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
        return new ArrayList<>();
    }

    @Override
    public List<Kweet> getKweetsFromMention(String mention)
    {
        return new ArrayList<>();
    }

    @Override
    public List<Kweet> getKweetsFromMention(
            String mention,
            int amount)
    {
        return new ArrayList<>();
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
        return new ArrayList<>();
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
        Kweet k = new Kweet(message, user);
        if (entityManager == null)
        {
            Logger.getAnonymousLogger().log(Level.WARNING, "entitymanager is null");
            return null;
        }
        entityManager.persist(k);

        return k;
    }

    @Override
    public boolean deleteKweet(long id)
    {
        return false;
    }

    @Override
    public List<Kweet> getKweetsForUserProfile(String userName)
    {
        return new ArrayList<>();
    }

    @Override
    public List<Kweet> getKweetsWithSQL(String sql)
    {
        return new ArrayList<>();
    }

    @Override
    public Kweet getKweet(long id)
    {
        return null;
    }

    @Override
    public List<String> getTends()
    {
        return new ArrayList<>();
    }
}