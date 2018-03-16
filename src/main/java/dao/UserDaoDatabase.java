package dao;

import classes.RolesEnum;
import classes.User;
import exceptions.IdAlreadyExistsException;
import exceptions.NoPermissionException;
import exceptions.UserNotFoundException;
import interfaces.UserDao;

import javax.ejb.Stateless;
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
    public List<User> getUsers() {
        return entityManager.createQuery("SELECT u FROM User u", User.class)
                            .getResultList();
    }

    @Override
    public User getUser(String userName) throws UserNotFoundException {

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
    }

    @Override
    public void setRole(
            String userName,
            RolesEnum role) throws NoPermissionException, UserNotFoundException
    {
        User u = getUser(userName);

        u.setRole(role);

        entityManager.merge(u);
    }

    @Override
    public User createUser(
            String username,
            String password,
            RolesEnum role) throws IdAlreadyExistsException
    {
        try
        {
            getUser(username);
            throw new IdAlreadyExistsException("User already exists");
        }
        catch (UserNotFoundException e)
        {
            // continue?
            User u = new User(username, role);
            entityManager.persist(u);

            return u;
        }
    }

    @Override
    public User updateUser(User user) throws NoPermissionException, UserNotFoundException {
        getUser(user.getUsername());

        entityManager.merge(user);

        return user;
    }
}
