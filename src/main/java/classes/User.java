package classes;

import java.util.*;

public class User {

	private String username;
	private String bio;
	private String location;
	private String website;
	private Set<String> following;
	private Set<String> followers;
	private String profilePicture;
	private RolesEnum role;
	private Set<Long> kweets;

    public User(String username)
    {
        this.username = username;
        following = new HashSet<>();
        followers = new HashSet<>();
        role = RolesEnum.User;

        kweets = new HashSet<>();
        //TODO link to a default profile picture
    }

    public String getUsername()
    {
        return username;
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

    public Set<Long> getKweets()
    {
        return kweets;
    }
}