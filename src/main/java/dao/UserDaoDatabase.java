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
    private EntityManager entityManager;

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
            User u = new User(username, group);
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
