package services;

import classes.RolesEnum;
import classes.User;
import exceptions.IdAlreadyExistsException;
import exceptions.NoPermissionException;
import exceptions.UserNotFoundException;
import interfaces.UserDao;

import javax.ejb.EJB;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
@Default
public class UserService
{
    private UserDao userDao;

    @EJB
    public void setUserDao(UserDao userDao)
    {
        this.userDao = userDao;
    }

    public List<User> getUsers()
    {
        return userDao.getUsers();
    }

    public User getUser(String userName) throws UserNotFoundException
    {
        return userDao.getUser(userName);
    }

    public void setRole(
            String userName,
            RolesEnum role) throws NoPermissionException, UserNotFoundException
    {
        userDao.setRole(userName, role);
    }

    public User createUser(
            String username,
            String password) throws IdAlreadyExistsException
    {
        return userDao.createUser(username, password);
    }

    public User createUser(
            String username,
            String password,
            RolesEnum role) throws IdAlreadyExistsException
    {
        return userDao.createUser(username, password, role);
    }
}
