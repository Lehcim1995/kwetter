package dao;

import classes.RolesEnum;
import classes.User;
import exceptions.IdAlreadyExistsException;
import exceptions.NoPermissionException;
import exceptions.UserNotFoundException;
import interfaces.UserDao;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
@Default
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
        return null;
    }

    @Override
    public User getUser(String userName) throws UserNotFoundException
    {
        return null;
    }

    @Override
    public void setRole(
            String userName,
            RolesEnum role) throws NoPermissionException
    {

    }

    @Override
    public User createUser(
            String username,
            String password,
            RolesEnum role) throws IdAlreadyExistsException
    {
        return null;
    }

}
