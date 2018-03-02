package classes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Kweet {

	private long id;
	private List<String> mentions;
	private List<String> harts;
	private List<String> trends;
	private String message;
	private String owner;
	private Date postDate;

    public Kweet(
            long id,
            String message,
            String owner)
    {

        if (owner.isEmpty())
        {
            throw new IllegalArgumentException();
        }

        if (message.isEmpty() || message.length() > 140 )
        {
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.message = message;
        this.owner = owner;
        mentions = new ArrayList<>();
        harts = new ArrayList<>();
        trends = new ArrayList<>();
        postDate = new Date();
    }

    public Kweet(
            long id,
            List<String> mentions,
            List<String> harts,
            List<String> trends,
            String message,
            String owner,
            Date postDate)
    {
        this(id, message, owner);

        this.mentions = mentions;
        this.harts = harts;
        this.trends = trends;
        this.postDate = postDate;
    }

    //https://stackoverflow.com/questions/29429074/extract-words-starting-with-a-particular-character-from-a-string
    private List<String> getMentionsFromMessage(String message)
    {
        List<String> mentions = new ArrayList<>();

        Pattern pattern = Pattern.compile("@\\w+");

        Matcher matcher = pattern.matcher(message);
        while (matcher.find())
        {
            mentions.add(matcher.group());
        }

        return mentions;
    }

    private List<String> getTrendsFromMessage(String message)
    {
        List<String> trends = new ArrayList<>();

        Pattern pattern = Pattern.compile("#\\w+");

        Matcher matcher = pattern.matcher(message);
        while (matcher.find())
        {
            trends.add(matcher.group());
        }

        return trends;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public void setOwner(String owner)
    {
        this.owner = owner;
    }

    public void setPostDate(Date postDate)
    {
        this.postDate = postDate;
    }

    public long getId()
    {
        return id;
    }

    public List<String> getMentions()
    {
        return mentions;
    }

    public List<String> getHarts()
    {
        return harts;
    }

    public List<String> getTrends()
    {
        return trends;
    }

    public String getMessage()
    {
        return message;
    }

    public String getOwner()
    {
        return owner;
    }

    public Date getPostDate()
    {
        return postDate;
    }
}