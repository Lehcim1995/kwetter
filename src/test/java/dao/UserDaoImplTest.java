package dao;

import classes.RolesEnum;
import classes.User;
import exceptions.CouldNotCreateUser;
import exceptions.IdAlreadyExistsException;
import exceptions.NoPermissionException;
import exceptions.UserNotFoundException;
import interfaces.UserDao;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.fail;

@RunWith(Parameterized.class)
public class UserDaoImplTest
{
    UserDao userDao;

    public UserDaoImplTest(UserDao userDao, String name){
        this.userDao = userDao;
    }

    // This makes this junit impl run multiple times but with different implementations
    // Very spicy
    @Parameterized.Parameters(name= "{1}")
    public static Collection<Object[]> getParameters() {
        return Arrays.asList(new Object[][] {
                { new UserDaoCollection() , "Collection" }
                // TODO add Database class
        });
    }

    @Before
    public void setUp() throws Exception
    {
        ((UserDaoCollection) userDao).init();

        // TODO create users here
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
            fail("cannot create users with same username");
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
            fail("Cannot contain duplicate username's");
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
            fail("cannot create users with same username");
        }

        try
        {
            userDao.getUser("hans"); // TODO shizzles
        }
        catch (UserNotFoundException e)
        {
            e.printStackTrace();
            fail("User does not excist");
        }

        // something else
    }

    @Test
    public void setRole()
    {

        try
        {
            userDao.createUser("hans", "password");
        }
        catch (IdAlreadyExistsException | CouldNotCreateUser e)
        {
            fail();
        }

        try
        {
            userDao.setRole("hans", RolesEnum.Moderator);
        }
        catch (NoPermissionException | UserNotFoundException e)
        {
            fail();
        }

        try
        {
            Assert.assertEquals(RolesEnum.Moderator, userDao.getUser("hans").getRole());
        }
        catch (UserNotFoundException e)
        {
            fail();
        }

    }

    @Test
    public void createUser()
    {
        String username = "username";
        String password = "password";

        try
        {
            userDao.createUser(username, password);
        }
        catch (IdAlreadyExistsException | CouldNotCreateUser e)
        {
            fail();
        }

        User u = null;

        try
        {
            u = userDao.getUser(username);
        }
        catch (UserNotFoundException e)
        {
            fail();
        }

        Assert.assertEquals(username, u.getUsername());
        Assert.assertEquals(RolesEnum.User, u.getRole());

    }
}