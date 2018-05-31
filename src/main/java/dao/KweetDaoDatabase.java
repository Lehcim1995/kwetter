package dao;

import classes.Kweet;
import classes.User;
import exceptions.KweetNotFoundException;
import exceptions.UserNotFoundException;
import interfaces.KweetDao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Stateless //stateless
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
        try
        {
            return entityManager.createQuery("SELECT k FROM Kweet k ORDER BY k.postDate desc", Kweet.class)
                                .getResultList();
        }
        catch (NoResultException e)
        {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Kweet> getKweets(int limit)
    {
        try
        {
            return entityManager.createQuery("SELECT k FROM Kweet k ORDER BY k.postDate desc ", Kweet.class)
                                .setMaxResults(limit)
                                .getResultList();
        }
        catch (NoResultException e)
        {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Kweet> getKweets(int limit, int offset)
    {
        try
        {
            return entityManager.createQuery("SELECT k FROM Kweet k ORDER BY k.postDate desc", Kweet.class)
                                .setMaxResults(limit)
                                .setFirstResult(offset)
                                .getResultList();
        }
        catch (NoResultException e)
        {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Kweet> getKweetsFromUser(String username)
    {
        return entityManager.createQuery("SELECT u.kweets FROM User u WHERE u.username = :owner", Kweet.class)
                            .setParameter("owner", username)
                            .getResultList();
    }

    @Override
    public List<Kweet> getKweetsFromUser(
            String username,
            int amount)
    {
        return entityManager.createQuery("SELECT u.kweets FROM User u WHERE u.username = :owner", Kweet.class)
                            .setParameter("owner", username)
                            .setMaxResults(amount)
                            .getResultList();
    }

    @Override
    public List<Kweet> getKweetsFromMention(String mention)
    {

        return entityManager.createQuery("SELECT k FROM Kweet k WHERE k.mentions in :mention", Kweet.class)
                            .setParameter("mention", mention)
                            .getResultList();
    }

    @Override
    public List<Kweet> getKweetsFromMention(
            String mention,
            int amount)
    {
        return entityManager.createQuery("SELECT k FROM Kweet k WHERE k.mentions in :mention", Kweet.class)
                            .setParameter("mention", mention)
                            .setMaxResults(amount)
                            .getResultList();
    }

    @Override
    public List<Kweet> getKweetsFromTrend(String trend)
    {

        return entityManager.createQuery("SELECT k FROM Kweet k WHERE k.trends in :trend", Kweet.class)
                            .setParameter("trend", trend)
                            .getResultList();
    }

    @Override
    public List<Kweet> getKweetsFromTrend(
            String trend,
            int amount)
    {
        return entityManager.createQuery("SELECT k FROM Kweet k WHERE k.trends in :trend", Kweet.class)
                            .setParameter("trend", trend)
                            .setMaxResults(amount)
                            .getResultList();
    }

    @Override
    public Kweet addKweet(
            String message,
            User user) throws UserNotFoundException
    {

        if (user == null)
        {
            throw new UserNotFoundException("ded");
        }

        Kweet k = new Kweet(message, user);
        entityManager.persist(k);

        return k;
    }

    @Override
    public boolean deleteKweet(long id) throws KweetNotFoundException
    {
        entityManager.remove(getKweet(id));

        return true;
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
    public Kweet getKweet(long id) throws KweetNotFoundException
    {
        Kweet k;

        try
        {
            k = entityManager.createQuery("SELECT k FROM Kweet k WHERE k.id = :id", Kweet.class)
                             .setParameter("id", id)
                             .getSingleResult();
        }
        catch (NoResultException e)
        {
            throw new KweetNotFoundException("Kweet with id " + id + " was not found");
        }

        return k;
    }

    @Override
    public List<String> getTends()
    {
        return entityManager.createQuery("SELECT k.trends FROM Kweet k", String.class)
                            .getResultList();

//        return entityManager.createQuery("SELECT distinct k.trends FROM Kweet k group by k.trends order by count(k.trends) desc", String.class)
//                            .getResultList();
    }

    @Override
    public List<String> getTends(int limit)
    {
        return entityManager.createQuery("SELECT k.trends FROM Kweet k", String.class)
                            .setMaxResults(limit)
                            .getResultList();

        // https://stackoverflow.com/questions/7001226/how-to-order-by-count-in-jpa
//        return entityManager.createQuery("SELECT distinct k.trends FROM Kweet k group by k.trends order by count(k.trends) desc", String.class)
//                            .setMaxResults(limit)
//                            .getResultList();
    }

    @Override
    public List<Kweet> searchKweets(String search) {
        return entityManager.createQuery("SELECT k FROM Kweet k WHERE k.message like :search", Kweet.class)
                            .setParameter("search", "%" + search + "%")
                            .getResultList();
    }
}