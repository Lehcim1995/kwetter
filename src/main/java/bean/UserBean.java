package bean;

import classes.User;
import services.KwetterService;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@ManagedBean
@Named(value="userBean")
public class UserBean
{
    @Inject
    KwetterService kwetterService;

    public List<User> getUsers() {
        return kwetterService.getUsers();
    }
}
