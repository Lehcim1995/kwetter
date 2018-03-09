package classes;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@NamedQueries({
        @NamedQuery(name = "kweet.getKweets", query = "SELECT K FROM Kweet K"),
        @NamedQuery(name = "kweet.getKweetsFromUser", query = "SELECT k FROM Kweet k where k.owner LIKE :owner")
})
public class Kweet implements Serializable
{

    @Id
    @GeneratedValue()
    private long id;

    @ElementCollection
    private List<String> mentions;

    @ElementCollection
    private Set<String> harts;

    @ElementCollection
    private List<String> trends;

    private String message;

    private String owner;

    private Date postDate;

    public Kweet()
    {
    }

    public Kweet(
            String message,
            String owner)
    {
        if (owner.isEmpty())
        {
            throw new IllegalArgumentException();
        }

        if (message.isEmpty() || message.length() > 140)
        {
            throw new IllegalArgumentException();
        }

        this.message = message;
        this.owner = owner;
        trends = getTrendsFromMessage(message);
        mentions = getMentionsFromMessage(message);
        harts = new HashSet<>();
        postDate = new Date();
    }

    public Kweet(
            long id,
            String message,
            String owner)
    {
        this(message, owner);
        this.id = id;
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

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
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

    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getOwner()
    {
        return owner;
    }

    public void setOwner(String owner)
    {
        this.owner = owner;
    }

    public Date getPostDate()
    {
        return postDate;
    }

    public void setPostDate(Date postDate)
    {
        this.postDate = postDate;
    }

    public void addHeart(String userName)
    {
        harts.add(userName);
    }
}