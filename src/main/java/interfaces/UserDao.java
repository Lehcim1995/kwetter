package interfaces;

import classes.RolesEnum;
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

    void setRole(String userName, RolesEnum role) throws NoPermissionException, UserNotFoundException;

    User updateUser(User user) throws NoPermissionException, UserNotFoundException;

    default User createUser(
        String username,
        String password) throws IdAlreadyExistsException, CouldNotCreateUser
    {return createUser(username, password, RolesEnum.User);}

    User createUser(String username, String password, RolesEnum role)
    throws IdAlreadyExistsException, CouldNotCreateUser;
}
