package classes;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
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

    @ElementCollection
    private Set<String> following;

    @ElementCollection
    private Set<String> followers;

    private String profilePicture;
    private RolesEnum role;

    @ElementCollection
    private Set<Long> kweets;

    public User()
    {
    }

    public User(
            String username,
            RolesEnum role)
    {
        this.username = username;
        following = new HashSet<>();
        followers = new HashSet<>();
        this.role = role;

        kweets = new HashSet<>();
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

    public void setBio(String bio)
    {
        this.bio = bio;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public void setWebsite(String website)
    {
        this.website = website;
    }

    public void setProfilePicture(String profilePicture)
    {
        this.profilePicture = profilePicture;
    }

    public String getBio()
    {
        return bio;
    }

    public String getLocation()
    {
        return location;
    }

    public String getWebsite()
    {
        return website;
    }

    public Set<String> getFollowing()
    {
        return following;
    }

    public Set<String> getFollowers()
    {
        return followers;
    }

    public String getProfilePicture()
    {
        return profilePicture;
    }

    public RolesEnum getRole()
    {
        return role;
    }

    public void setRole(RolesEnum role)
    {
        this.role = role;
    }

    public Set<Long> getKweets()
    {
        return kweets;
    }
}