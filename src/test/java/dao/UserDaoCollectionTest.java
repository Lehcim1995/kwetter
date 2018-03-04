package dao;

import classes.User;
import exceptions.IdAlreadyExistsException;
import exceptions.UserNotFoundException;
import interfaces.UserDao;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class UserDaoCollectionTest
{

    UserDao userDao = new UserDaoCollection();

    @Before
    public void setUp() throws Exception
    {
        ((UserDaoCollection) userDao).init();
    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void getUsers()
    {
        // TODO add users
        try
        {
            userDao.createUser("hans", "password");
            userDao.createUser("hans1", "password");
            userDao.createUser("hans2", "password");
            userDao.createUser("hans3", "password");
            userDao.createUser("hans4", "password");
        }
        catch (Exception e)
        {
            Assert.fail("cannot create users with same username");
        }

        int expected = 5;

        List<User> output = userDao.getUsers();

        Assert.assertEquals(expected, output.size());
    }

    @Test
    public void getUsers2()
    {
        // TODO add users
        try
        {
            userDao.createUser("hans", "password");
            userDao.createUser("hans", "password");
            userDao.createUser("hans2", "password");
            userDao.createUser("hans3", "password");
            userDao.createUser("hans4", "password");
            Assert.fail("Cannot contain duplicate username's");
        }
        catch (Exception e)
        {
            // yay
        }

        int expected = 5;

        List<User> output = userDao.getUsers();

        Assert.assertNotEquals(expected, output.size());
    }

    @Test
    public void getUser()
    {
        try
        {
            userDao.createUser("hans", "password");
            userDao.createUser("hans1", "password");
            userDao.createUser("hans2", "password");
            userDao.createUser("hans3", "password");
            userDao.createUser("hans4", "password");
        }
        catch (Exception e)
        {
            Assert.fail("cannot create users with same username");
        }

        try
        {
            userDao.getUser("hans"); // TODO shizzles
        }
        catch (UserNotFoundException e)
        {
            e.printStackTrace();
            Assert.fail("User does not excist");
        }

        // something else
    }

    @Test
    public void setRole()
    {
        // TODO
    }

    @Test
    public void createUser()
    {
        // TODO

        // add role test and normal test
    }
}