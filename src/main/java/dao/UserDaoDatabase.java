package dao;

import classes.Group;
import classes.User;
import exceptions.CouldNotCreateUser;
import exceptions.IdAlreadyExistsException;
import exceptions.NoPermissionException;
import exceptions.UserNotFoundException;
import interfaces.UserDao;

import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@JPA
public class UserDaoDatabase implements UserDao
{
    @PersistenceContext(unitName = "kwetterPU")
    EntityManager entityManager;

    @Override
    public User login(
            final String username,
            final String password) throws UserNotFoundException
    {
        final String passhash = org.apache.commons.codec.digest.DigestUtils.sha256Hex(password);

        try
        {


            User u = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username AND u.password = :password", User.class)
                         .setParameter("username", username)
                         .setParameter("password", passhash)
                         .getSingleResult();

            return u;
        }
        catch (Exception e) // If any exception is thrown dont log in
        {
            throw new UserNotFoundException("asd");
        }
    }

    @Override
    public List<User> getUsers()
    {
        return entityManager.createQuery("SELECT u FROM User u", User.class)
                            .getResultList();
    }

    @Override
    public User getUser(String userName) throws UserNotFoundException
    {

        try
        {
            return entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                                .setParameter("username", userName)
                                .getSingleResult();
        }
        catch (NoResultException e)
        {
            System.out.println("User " + userName + " was not found");
            throw new UserNotFoundException("User " + userName + " was not found");
        }
        catch (Exception e)
        {
            System.out.println("Exception general");
            throw new UserNotFoundException("User " + userName + " was not found");
        }
    }

    @Override
    public void addGroup(
            String userName,
            Group group) throws NoPermissionException, UserNotFoundException
    {
        User u = getUser(userName);

        u.addGroup(group);

        entityManager.merge(u);
    }

    @Override
    public User createUser(
            String username,
            String password,
            Group group) throws IdAlreadyExistsException, CouldNotCreateUser
    {
        try
        {
            User u = new User(username, password, group);
            entityManager.persist(u);


            return u;
        }
        catch (EntityExistsException eee)
        {
            System.out.println(eee.getMessage());
            throw new IdAlreadyExistsException("user");
        }
        catch (Exception e)
        {
            System.out.println(e.getClass());
            System.out.println(e.getMessage());
            throw new CouldNotCreateUser();
        }
    }

    @Override
    public User updateUser(User user) throws NoPermissionException, UserNotFoundException {
        getUser(user.getUsername());

        entityManager.merge(user);

        return user;
    }
}
