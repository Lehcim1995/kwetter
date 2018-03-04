package services;

import classes.RolesEnum;
import classes.User;
import exceptions.IdAlreadyExistsException;
import exceptions.NoPermissionException;
import exceptions.UserNotFoundException;
import interfaces.UserDao;

import javax.inject.Inject;
import java.util.List;

public class UserService
{
    private UserDao userDao;

    @Inject
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
