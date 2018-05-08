package classes;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity(name = "Kweet")
@Table(name = "kweet")
@NamedQueries({@NamedQuery(name = "kweet.getKweets", query = "SELECT K FROM Kweet K"), @NamedQuery(name = "kweet.getKweetsFromUser", query = "SELECT k FROM Kweet k where k.owner.username = :owner")})
public class Kweet implements Serializable
{

    @Id
    @GeneratedValue()
    private long id;

    //    @OneToMany(cascade = CascadeType.PERSIST)
    @ElementCollection
    private List<String> mentions = new ArrayList<>(); // make users

    //    @OneToMany(cascade = CascadeType.PERSIST)
    @ElementCollection
    private List<String> harts = new ArrayList<>();

    @ElementCollection
    private List<String> trends = new ArrayList<>();

    private String message;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonIgnore
    @XmlTransient
    private User owner;

    private String ownerName;
    //TODO add owner name

    private Date postDate;

    public Kweet()
    {
    }

    public Kweet(String message)
    {

        if (message.isEmpty() || message.length() > 140)
        {
            throw new IllegalArgumentException();
        }

        this.message = message;
        trends = getTrendsFromMessage(message);
        mentions = getMentionsFromMessage(message);/*.stream()
                                                  .map(User::new)
                                                  .collect(Collectors.toList());*/
        harts = new ArrayList<>();
        postDate = new Date();
    }

    public Kweet(
            String message,
            User owner)
    {
        this(message);

        if (owner == null)
        {
            throw new IllegalArgumentException();
        }

        setOwner(owner);
    }

    public Kweet(
            long id,
            String message,
            User owner)
    {
        this(message, owner);
        this.id = id;
    }

    public Kweet(
            long id,
            List<User> mentions,
            List<User> harts,
            List<String> trends,
            String message,
            User owner,
            Date postDate)
    {
        this(id, message, owner);

        this.mentions = new ArrayList<>();
        this.harts = new ArrayList<>();
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

    public boolean like(User user)
    {
        if (harts.contains(user.getUsername()))
        {
            return false;
        }

        harts.add(user.getUsername());
        return true;
    }

    public boolean unLike(User user)
    {
        if (harts.contains(user.getUsername()))
        {
            harts.remove(user.getUsername());
            return true;
        }

        return false;
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
        return mentions/*.stream()
                       .map(User::getUsername)
                       .collect(Collectors.toList())*/;
    }

    public List<String> getHarts()
    {
        return harts/*.stream()
                    .map(User::getUsername)
                    .collect(Collectors.toList())*/;
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

    public String getOwnerName()
    {
        if (owner == null)
        {
            return "";
        }
        return owner.getUsername();
    }

    @JsonIgnore
    public User getOwner()
    {
        return owner;
    }

    public void setOwner(User owner)
    {
        this.ownerName = owner.getUsername();
        this.owner = owner;
        if (!owner.getKweets()
                  .contains(this))
        { // warning this may cause performance issues if you have a large data set since this operation is O(n)
            owner.addKweet(this);
        }
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
        harts.add(null); // test todo fix
    }
}