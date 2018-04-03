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

    private List<Kweet> kweets;

    public List<Kweet> getKweets()
    {
        if (kweets == null || kweets.isEmpty())
        {
            kweets = kwetterService.getKweets();
        }

        return kweets;
    }

    public Kweet getKweet(long id) throws KweetNotFoundException
    {
        return kwetterService.getKweet(id);
    }

    public void setKweets(List<Kweet> kweets) {
        this.kweets = kweets;
    }

    public boolean deleteKweet(long id) throws KweetNotFoundException
    {
        return kwetterService.deleteKweet(id);
    }
}
