package bean;

import classes.Kweet;
import services.KwetterService;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@ManagedBean
@Named(value="kweetBean")
public class KweetBean
{
    @Inject
    KwetterService kwetterService;

    public List<Kweet> getKweets() {
        return kwetterService.getKweets();
    }
}
