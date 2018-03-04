package classes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Kweet {

	private long id;
	private List<String> mentions;
	private Set<String> harts;
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
        mentions = getMentionsFromMessage(message);
        harts = new HashSet<>();
        trends = getTrendsFromMessage(message);
        postDate = new Date();
    }

    public Kweet(
            long id,
            List<String> mentions,
            Set<String> harts,
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
    public static List<String> getMentionsFromMessage(String message)
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

    public static List<String> getTrendsFromMessage(String message)
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
        return new ArrayList<>(harts);
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

    public void addHeart(String userName)
    {
        harts.add(userName);
    }
}