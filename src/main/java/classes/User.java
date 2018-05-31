package classes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import exceptions.UserAlreadyFollowing;
import exceptions.UserNotFollowing;
import json.Exclude;
import org.apache.commons.codec.digest.DigestUtils;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "User")
@Table(name = "kwetter_user")
@XmlAccessorType(XmlAccessType.FIELD)
public class User implements Serializable
{

    @Id
    @GeneratedValue
    private String username;

    private String bio;
    private String location;
    private String website;


    @Exclude
    @JsonIgnore
    private String password;

//    @ManyToMany
//    private List<User> following = new ArrayList<>();

    @ManyToMany
    private List<User> followers = new ArrayList<>();

    //    @OneToMany(cascade = CascadeType.PERSIST)
    @ElementCollection
    private List<String> mentions = new ArrayList<>();

    //    @OneToMany(cascade = CascadeType.PERSIST)
    @ElementCollection
    private List<String> harts = new ArrayList<>();

    private String profilePicture = "";

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(joinColumns = @JoinColumn(name = "USERNAME", referencedColumnName = "USERNAME"), inverseJoinColumns = @JoinColumn(name = "GROUPNAME", referencedColumnName = "GROUPNAME"))
    private Collection<Group> groups;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private List<Kweet> kweets = new ArrayList<>();

    public User()
    {
    }

    public User(
            String username,
            Group group)
    {
       this(username, "password", group);
    }

    public User(
            String username,
            String password,
            Group group)
    {
        this.username = username;
//        following = new ArrayList<>();
        followers = new ArrayList<>();

        groups = new ArrayList<>();
        groups.add(group); // TODO fix

        kweets = new ArrayList<>();

        this.password = DigestUtils.sha256Hex(password);
        //TODO link to a default profile picture
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
//        return following.stream()
//                        .map(User::getUsername)
//                        .collect(Collectors.toList());
        return new ArrayList<>();
    }

    public void setFollowing(List<User> following) {
        /*this.following = following;*/
    }

    public List<String> getFollowers()
    {
        return followers.stream()
                        .map(User::getUsername)
                        .collect(Collectors.toList());
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    public String getProfilePicture()
    {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture)
    {
        this.profilePicture = profilePicture;
    }

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
//        following.add(user);
    }

    public void unfollow(User user) throws UserNotFollowing
    {
        if (!user.followers.contains(this))
        {
            throw new UserNotFollowing();
        }
        user.followers.remove(this);
//        following.remove(user);
    }

    public Collection<Group> getGroups() {
        return groups;
    }

    public void setGroups(Collection<Group> groups) {
        this.groups = groups;
    }

    public void removeGroup(Group groupName)
    {
        groups.remove(groupName);
    }

    public void addGroup(Group groupName)
    {
        groups.add(groupName);
    }

    public List<String> getMentions() {
        return mentions;
    }

    public void setMentions(List<String> mentions) {
        this.mentions = mentions;
    }

    public List<String> getHarts() {
        return harts;
    }

    public void setHarts(List<String> harts) {
        this.harts = harts;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}