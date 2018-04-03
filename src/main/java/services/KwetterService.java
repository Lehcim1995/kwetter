package services;

import classes.Kweet;
import classes.RolesEnum;
import classes.User;
import dao.JPA;
import exceptions.*;
import interfaces.KweetDao;
import interfaces.UserDao;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@Stateless
@Default
public class KwetterService implements Serializable
{
    @Inject
    @JPA
    private KweetDao kweetDao;

    @Inject
    @JPA
    private UserDao userDao;

    @PostConstruct
    public void init()
    {
//        try
//        {
//            createUser("user1", "");
//
//        }
//        catch (IdAlreadyExistsException e1)
//        {
//            System.out.println("User already exists");
////            return;
//        }
//        catch (CouldNotCreateUser couldNotCreateUser)
//        {
//            couldNotCreateUser.printStackTrace();
//            //return
//        }
//
//        try
//        {
//            addKweet("message 1", "user1");
//            addKweet("message 2", "user1");
//            addKweet("message 3", "user1");
//            addKweet("message 4", "user1");
//        }
//        catch (UserNotFoundException e2)
//        {
//            System.out.println("User doesn't exists");
//        }
    }

    public List<Kweet> getKweets()
    {
        return kweetDao.getKweets();
    }

    public List<Kweet> getKweets(int limit) {
        return kweetDao.getKweets(limit);
    }

    public List<Kweet> getKweetsFromUser(String username) {
        return kweetDao.getKweetsFromUser(username);
    }

    public List<Kweet> getKweetsFromUser(
            String username,
            int amount)
    {
        return kweetDao.getKweetsFromUser(username, amount);
    }

    public List<Kweet> getKweetsFromMention(String mention) {
        return kweetDao.getKweetsFromMention(mention);
    }

    public List<Kweet> getKweetsFromMention(
            String mention,
            int amount)
    {
        return kweetDao.getKweetsFromMention(mention, amount);
    }

    public List<Kweet> getKweetsFromTrend(String trend) {
        return kweetDao.getKweetsFromTrend(trend);
    }

    public List<Kweet> getKweetsFromTrend(
            String trend,
            int amount)
    {
        return kweetDao.getKweetsFromTrend(trend, amount);
    }

    public Kweet addKweet(
            String message,
            String user) throws UserNotFoundException
    {
        User realUser = userDao.getUser(user);

        System.out.println("user is created : " + (realUser != null));

        return kweetDao.addKweet(message, realUser);
    }

    public boolean deleteKweet(long id) throws KweetNotFoundException {
        return kweetDao.deleteKweet(id);
    }

    public List<Kweet> getKweetsForUserProfile(String userName) {
        return kweetDao.getKweetsForUserProfile(userName);
    }

    public List<Kweet> getKweetsWithSQL(String sql) {
        return kweetDao.getKweetsWithSQL(sql);
    }

    public Kweet getKweet(long id) throws KweetNotFoundException {
        return kweetDao.getKweet(id);
    }

    public List<String> getTends() {
        return kweetDao.getTends();
    }

    public List<String> getTends(int limit) {
        return kweetDao.getTends(limit);
    }

    public List<Kweet> searchKweets(String search) {
        return kweetDao.searchKweets(search);
    }

    public List<User> getUsers() {
        return userDao.getUsers();
    }

    public User getUser(String userName) throws UserNotFoundException {
        return userDao.getUser(userName);
    }

    public void setRole(
            String userName,
            RolesEnum role) throws NoPermissionException, UserNotFoundException
    {
        userDao.setRole(userName, role);
    }

    public User updateUser(User user) throws NoPermissionException, UserNotFoundException {
        return userDao.updateUser(user);
    }

    public User createUser(
            String username,
            String password) throws IdAlreadyExistsException, CouldNotCreateUser
    {
        return userDao.createUser(username, password);
    }

    public User createUser(
            String username,
            String password,
            RolesEnum role) throws IdAlreadyExistsException, CouldNotCreateUser
    {
        return userDao.createUser(username, password, role);
    }

    public void follow(User user, User follower) throws UserAlreadyFollowing, UserNotFoundException
    {
        userDao.getUser(follower.getUsername()).follow(user);
    }

    public void unfollow(User user, User follower) throws UserNotFollowing, UserNotFoundException
    {
        userDao.getUser(follower.getUsername()).unfollow(user);
    }

    public void likeKweet(User user, Kweet kweet) throws KweetNotFoundException
    {
        kweetDao.getKweet(kweet.getId()).like(user);
    }

    public void unlikeKweet(User user, Kweet kweet) throws KweetNotFoundException
    {
        kweetDao.getKweet(kweet.getId()).unLike(user);
    }

}
