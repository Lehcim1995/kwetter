package classes;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

    @ElementCollection
    private Set<String> following;

    @ElementCollection
    private Set<String> followers;

    private String profilePicture;
    private RolesEnum role;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    @JoinColumn(name = "ID") // join table?
    @JoinTable(name = "user_kweets")
    private List<Kweet> kweets;

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

    public List<Long> getKweets()
    {
//        return kweets.stream()
//                     .map(Kweet::getId)
//                     .collect(Collectors.toSet());

        return kweets.stream()
                     .map(Kweet::getId)
                     .collect(Collectors.toList());
    }
}