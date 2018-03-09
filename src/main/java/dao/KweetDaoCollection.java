package dao;

import classes.Kweet;
import exceptions.KweetNotFoundException;
import interfaces.KweetDao;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Alternative;
import javax.inject.Singleton;
import java.util.*;
import java.util.stream.Collectors;

@Singleton
@Alternative
public class KweetDaoCollection implements KweetDao
{
    private Set<String> trends;
    private Map<Long, Kweet> kweets;

    @PostConstruct
    public void init()
    {
        kweets = new HashMap<>();
        trends = new HashSet<>();
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
        String mentionToken = MENTION_TOKEN + mention;

        return kweets.entrySet()
                .stream()
                .filter(longKweetEntry -> longKweetEntry.getValue().getMentions().contains(mentionToken))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    @Override
    public List<Kweet> getKweetsFromMention(
            String mention,
            int amount)
    {
        String mentionToken = MENTION_TOKEN + mention;

        return kweets.entrySet()
                .stream()
                .filter(longKweetEntry -> longKweetEntry.getValue().getMentions().contains(mentionToken))
                .map(Map.Entry::getValue)
                .limit(amount)
                .collect(Collectors.toList());
    }

    @Override
    public List<Kweet> getKweetsFromTrend(String trend)
    {
        String trendToken = TREND_TOKEN + trend;

        return kweets.entrySet()
                .stream()
                .filter(longKweetEntry -> longKweetEntry.getValue().getTrends().contains(trendToken))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    @Override
    public List<Kweet> getKweetsFromTrend(
            String trend,
            int amount)
    {
        String trendToken = TREND_TOKEN + trend;

        return kweets.entrySet()
                .stream()
                .filter(longKweetEntry -> longKweetEntry.getValue().getTrends().contains(trendToken))
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
        long id = kweets.size() + 1L;
        Kweet kweet = new Kweet(id, message, user);
        trends.addAll(Kweet.getTrendsFromMessage(message));
        kweets.put(id, kweet);

        return kweet;
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
    public Kweet getKweet(long id) throws KweetNotFoundException
    {
        Kweet kweet = kweets.get(id);

        if (kweet == null)
        {
            throw new KweetNotFoundException("Kweet " + id + " not found");
        }

        return kweet;
    }

    @Override
    public List<String> getTends()
    {
        return new ArrayList<>(trends);
    }
}