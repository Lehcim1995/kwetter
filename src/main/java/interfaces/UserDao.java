package interfaces;

import classes.Group;
import classes.User;
import exceptions.CouldNotCreateUser;
import exceptions.IdAlreadyExistsException;
import exceptions.NoPermissionException;
import exceptions.UserNotFoundException;

import java.util.List;

public interface UserDao
{
    User login(
            String username,
            String password) throws UserNotFoundException;

    List<User> getUsers();

    User getUser(String userName) throws UserNotFoundException;

    void addGroup(String userName, Group group) throws NoPermissionException, UserNotFoundException;

    User updateUser(User user) throws NoPermissionException, UserNotFoundException;

    User createUser(String username, String password, Group group)
    throws IdAlreadyExistsException, CouldNotCreateUser;
}
