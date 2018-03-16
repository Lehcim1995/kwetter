package classes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

    @ElementCollection
    private List<String> following;

    @ElementCollection
    private List<String> followers;

    private String profilePicture;
    private RolesEnum role;

    @OneToMany(mappedBy = "owner")
    @JsonManagedReference
    private List<Kweet> kweets;

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
        return following;
    }

    public List<String> getFollowers()
    {
        return followers;
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

        Kweet k = new Kweet(message, this);

        kweets.add(k);

        return k;
    }

    public Kweet addKweet(Kweet k) {

        kweets.add(k);
        if (k.getOwner() != this) {
            k.setOwner(this);
        }

        return k;
    }
}