package dao;

import classes.Kweet;
import classes.User;
import exceptions.KweetNotFoundException;
import interceptors.PermisionInceptor;
import interfaces.KweetDao;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.interceptor.Interceptors;
import java.util.*;
import java.util.stream.Collectors;

@ApplicationScoped
@Default
public class KweetDaoCollection implements KweetDao
{
    private Set<String> trends;
    private Map<Long, Kweet> kweets;

    private void createDummydata()
    {
        addKweet("01 @jan #r");
        addKweet("02 #1");
        addKweet("03 #2");
        addKweet("04 #3");
        addKweet("05 #4 #3 #6 #1");
        addKweet("06 #4");
        addKweet("07 #4");
        addKweet("08 #4");
        addKweet("09 #4");
        addKweet("10 #4");
        addKweet("11 #4");
    }

    @PostConstruct
    public void init()
    {
        kweets = new HashMap<>();
        trends = new HashSet<>();
//        createDummydata();
    }

    @Override
    public List<Kweet> getKweets()
    {
        return new ArrayList<>(kweets.values());
    }

    @Override
    public List<Kweet> getKweetsFromUser(String username)
    {
        return kweets.entrySet()
                .stream()
                .filter(longKweetEntry -> longKweetEntry.getValue().getOwner().equals(username))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    @Override
    public List<Kweet> getKweetsFromUser(
            String username,
            int amount)
    {
        return kweets.entrySet()
                .stream()
                .filter(longKweetEntry -> longKweetEntry.getValue().getOwner().equals(username))
                .map(Map.Entry::getValue)
                .limit(amount)
                .collect(Collectors.toList());
    }

    @Override
    public List<Kweet> getKweetsFromMention(String mention)
    {
        return kweets.entrySet()
                .stream()
                .filter(longKweetEntry -> longKweetEntry.getValue().getMentions().contains(mention))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    @Override
    public List<Kweet> getKweetsFromMention(
            String mention,
            int amount)
    {
        return kweets.entrySet()
                .stream()
                .filter(longKweetEntry -> longKweetEntry.getValue().getMentions().contains(mention))
                .map(Map.Entry::getValue)
                .limit(amount)
                .collect(Collectors.toList());
    }

    @Override
    public List<Kweet> getKweetsFromTrend(String trend)
    {
        return kweets.entrySet()
                .stream()
                .filter(longKweetEntry -> longKweetEntry.getValue().getTrends().contains(trend))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    @Override
    public List<Kweet> getKweetsFromTrend(
            String trend,
            int amount)
    {
        return kweets.entrySet()
                .stream()
                .filter(longKweetEntry -> longKweetEntry.getValue().getTrends().contains(trend))
                .map(Map.Entry::getValue)
                .limit(amount)
                .collect(Collectors.toList());
    }

    @Override
    @Deprecated
    public Kweet addKweet(String message)
    {
        return addKweet(message, "Test user");
    }

    @Override
    public Kweet addKweet(
            String message,
            String user)
    {
        long id = kweets.size() + 1;
        Kweet kweet = new Kweet(id, message, user);
        trends.addAll(Kweet.getTrendsFromMessage(message));

        return kweets.put(id, kweet);
    }

    @Override
    public boolean deleteKweet(long id) throws KweetNotFoundException
    {
        if (!kweets.containsKey(id))
        {
            throw new KweetNotFoundException("");
        }

        kweets.remove(id);

        return true;
    }

    @Override // get mentions
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
        return kweets.get(id);
    }

    @Override
    public List<String> getTends()
    {
        return new ArrayList<>(trends);
    }
}