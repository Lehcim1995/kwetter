package dao;

import classes.RolesEnum;
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
    public void setRole(
            String userName,
            RolesEnum role) throws NoPermissionException, UserNotFoundException
    {
        if (!users.containsKey(userName))
        {
            throw new UserNotFoundException("user " + userName + " does not exist");
        }

        users.get(userName)
             .setRole(role);
        // TODO add setter for role
    }

    @Override
    public User createUser(
            String username,
            String password,
            RolesEnum role) throws IdAlreadyExistsException
    {
        if (users.containsKey(username))
        {
            throw new IdAlreadyExistsException("user " + username + " does not exist");
        }

        User u = new User(username, role);

        users.put(username, u);

        return u;
    }

}
