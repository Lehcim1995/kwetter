package classes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import exceptions.UserAlreadyFollowing;
import exceptions.UserNotFollowing;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "User")
@Table(name = "kwetter_user")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class User implements Serializable
{

    @Id
    @GeneratedValue
    private String username;

    private String bio;
    private String location;
    private String website;

    @XmlTransient // Never display password in a xml or json format
    @JsonIgnore
    private String password;

    @ManyToMany
    private List<User> following = new ArrayList<>();

    @ManyToMany
    private List<User> followers = new ArrayList<>();

    //    @OneToMany(cascade = CascadeType.PERSIST)
    @ElementCollection
    private List<String> mentions = new ArrayList<>();

    //    @OneToMany(cascade = CascadeType.PERSIST)
    @ElementCollection
    private List<String> harts = new ArrayList<>();

    private String profilePicture;
    private RolesEnum role;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private Collection<Group> groups;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JsonIgnore
    @XmlTransient
    private List<Kweet> kweets = new ArrayList<>();

    public User()
    {
    }

    public User(
            String username,
            RolesEnum role)
    {
        this.username = username;
        following = new ArrayList<>();
        followers = new ArrayList<>();
        this.role = role;

        kweets = new ArrayList<>();
        //TODO link to a default profile picture
    }

    public User(String username)
    {
        this(username, RolesEnum.User);
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getBio()
    {
        return bio;
    }

    public void setBio(String bio)
    {
        this.bio = bio;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public String getWebsite()
    {
        return website;
    }

    public void setWebsite(String website)
    {
        this.website = website;
    }

    public List<String> getFollowing()
    {
        return following.stream()
                        .map(User::getUsername)
                        .collect(Collectors.toList());
    }

    public List<String> getFollowers()
    {
        return followers.stream()
                        .map(User::getUsername)
                        .collect(Collectors.toList());
    }

    public String getProfilePicture()
    {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture)
    {
        this.profilePicture = profilePicture;
    }

    public RolesEnum getRole()
    {
        return role;
    }

    public void setRole(RolesEnum role)
    {
        this.role = role;
    }

    @JsonIgnore
    public List<Kweet> getKweets()
    {
        return kweets;
    }

    public Kweet addKweet(String message) {

        return addKweet(new Kweet(message, this));
    }

    public Kweet addKweet(Kweet k) {

        kweets.add(k);
        if (k.getOwner() != this)
        {
            k.setOwner(this);
        }

        return k;
    }

    public void follow(User user) throws UserAlreadyFollowing
    {
        if (user.followers.contains(this))
        {
            throw new UserAlreadyFollowing();
        }
        user.followers.add(this);
        following.add(user);
    }

    public void unfollow(User user) throws UserNotFollowing
    {
        if (!user.followers.contains(this))
        {
            throw new UserNotFollowing();
        }
        user.followers.remove(this);
        following.remove(user);
    }
}