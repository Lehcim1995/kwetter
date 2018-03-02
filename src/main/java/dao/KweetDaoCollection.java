package dao;

import classes.Kweet;
import classes.User;
import interfaces.KweetDao;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@ApplicationScoped
@Default
public class KweetDaoCollection implements KweetDao
{

    private List<User> users;
    private List<String> trends;
    private Map<Long, Kweet> kweets;

    @PostConstruct
    public void init()
    {
        users = new ArrayList<>();
        kweets = new HashMap<>();
        Logger.getAnonymousLogger().log(Level.INFO, "Ready to work");
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
    public Kweet addKweet(String message)
    {
        long id = kweets.size() + 1;
        Kweet kweet = new Kweet(id, message, "Jan");

        return kweets.put(id, kweet);
    }

    @Override
    public boolean deleteKweet(long id)
    {
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
    public List<String> getMentions()
    {
        return new ArrayList<>();
    }

    @Override
    public List<String> getTends()
    {
        return trends;
    }
}