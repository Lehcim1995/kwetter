package dao;

import interfaces.UserDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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


    }

    @Test
    public void getUser()
    {


    }

    @Test
    public void setRole()
    {


    }

    @Test
    public void createUser()
    {


    }
}