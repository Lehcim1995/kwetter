package bean;

import classes.Kweet;
import exceptions.KweetNotFoundException;
import services.KwetterService;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@Named(value = "kweetBean")
public class KweetBean implements Serializable
{
    @Inject
    private KwetterService kwetterService;

    public List<Kweet> getKweets()
    {
        return kwetterService.getKweets();
    }

    public Kweet getKweet(long id) throws KweetNotFoundException
    {
        return kwetterService.getKweet(id);
    }

    public boolean deleteKweet(long id) throws KweetNotFoundException
    {
        return kwetterService.deleteKweet(id);
    }
}
