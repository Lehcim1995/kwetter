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
    List<User> getUsers();

    User getUser(String userName) throws UserNotFoundException;

    void addGroup(String userName, String group) throws NoPermissionException, UserNotFoundException;

    User updateUser(User user) throws NoPermissionException, UserNotFoundException;

    default User createUser(
        String username,
        String password) throws IdAlreadyExistsException, CouldNotCreateUser
    {return createUser(username, password, Group.USER_GROUP);}

    User createUser(String username, String password, String group)
    throws IdAlreadyExistsException, CouldNotCreateUser;
}
