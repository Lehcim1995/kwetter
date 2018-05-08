package dao;

import classes.Group;
import classes.User;
import exceptions.IdAlreadyExistsException;
import exceptions.NoPermissionException;
import exceptions.UserNotFoundException;
import interfaces.UserDao;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
@Alternative
public class UserDaoCollection implements UserDao
{

    private Map<String, User> users;

    @PostConstruct
    public void init()
    {
        users = new HashMap<>();

        // TODO add some users
    }

    // https://avaldes.com/jax-rs-security-using-basic-authentication-and-authorization/
    @Override
    public List<User> getUsers()
    {
        return new ArrayList<>(users.values());
    }

    @Override
    public User getUser(String userName) throws UserNotFoundException
    {
        if (!users.containsKey(userName))
        {
            throw new UserNotFoundException("user " + userName + " does not exist");
        }

        return users.get(userName);
    }

    @Override
    public void addGroup(
            String userName,
            Group group) throws NoPermissionException, UserNotFoundException
    {
        if (!users.containsKey(userName))
        {
            throw new UserNotFoundException("user " + userName + " does not exist");
        }

        users.get(userName)
             .addGroup(group);
        // TODO add setter for role
    }

    @Override
    public User createUser(
            String username,
            String password,
            Group group) throws IdAlreadyExistsException
    {
        if (users.containsKey(username))
        {
            throw new IdAlreadyExistsException("user " + username + " does not exist");
        }

        User u = new User(username, group);

        users.put(username, u);

        return u;
    }

    @Override
    public User updateUser(User user) throws NoPermissionException, UserNotFoundException {
        return null; // TODO
    }
}
