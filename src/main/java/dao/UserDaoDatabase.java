package dao;

import classes.RolesEnum;
import classes.User;
import exceptions.IdAlreadyExistsException;
import exceptions.NoPermissionException;
import exceptions.UserNotFoundException;
import interfaces.UserDao;

import javax.ejb.Singleton;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Singleton
@Default
@JPA
public class UserDaoDatabase implements UserDao
{
    @PersistenceContext(unitName = "kwetterPU")
    private EntityManager entityManager;

    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public User getUser(String userName) throws UserNotFoundException {
        return null;
    }

    @Override
    public void setRole(
            String userName,
            RolesEnum role) throws NoPermissionException, UserNotFoundException
    {

    }

    @Override
    public User createUser(
            String username,
            String password,
            RolesEnum role) throws IdAlreadyExistsException
    {
        User u = new User(username, role);
        entityManager.persist(u);

        return u;
    }
}
