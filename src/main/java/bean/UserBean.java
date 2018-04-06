package bean;

import classes.Group;
import classes.User;
import exceptions.UserNotFoundException;
import services.KwetterService;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@SessionScoped
@Named(value = "userBean")
public class UserBean implements Serializable
{
    @Inject
    private KwetterService kwetterService;

    public List<User> getUsers() {
        return kwetterService.getUsers();
    }

    public void deleteUser(String username)
    {
//        kwetterService.d
    }

    public List<String> getUserGroups()
    {
        return Arrays.asList(Group.USER_GROUP, Group.ADMIN_GROUP);
    }

    public User getUser(String username)
    {
        try
        {
            return kwetterService.getUser(username);
        }
        catch (UserNotFoundException e)
        {
            return null;
        }
    }
}
