package bean;

import classes.Kweet;
import exceptions.KweetNotFoundException;
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

    public Kweet getKweet(long id) throws KweetNotFoundException {
        return kwetterService.getKweet(id);
    }
}
