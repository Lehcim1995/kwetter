package bean;

import classes.User;
import services.KwetterService;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@ManagedBean
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
}
