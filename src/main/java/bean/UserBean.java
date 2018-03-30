package bean;

import classes.User;
import services.KwetterService;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
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
}
