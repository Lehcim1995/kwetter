package bean;

import classes.Kweet;
import exceptions.KweetNotFoundException;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CellEditEvent;
import services.KwetterService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@SessionScoped
@Named(value = "kweetBean")
public class KweetBean implements Serializable
{
    @Inject
    private KwetterService kwetterService;

    private List<Kweet> kweets;

    @PostConstruct
    public void init()
    {
        kweets = kwetterService.getKweets();
    }

    public List<Kweet> getKweets()
    {
        return kweets;
    }

    public Kweet getKweet(long id) throws KweetNotFoundException
    {
        return kwetterService.getKweet(id);
    }

    public void refreshKweets()
    {
        kweets = kwetterService.getKweets();
    }

    public void setKweets(List<Kweet> kweets) {
        this.kweets = kweets;
    }

    public void deleteKweet(Kweet k) throws KweetNotFoundException
    {
        kwetterService.deleteKweet(k.getId());
        kweets.remove(k);
    }

    public void setKweetMessage(long id, String newMessage)
    {
        try
        {
            kwetterService.getKweet(id).setMessage(newMessage);
        }
        catch (KweetNotFoundException e)
        {

        }
    }

    public void editKweetMessage(CellEditEvent event) {
        String oldMessage = event.getOldValue().toString();
        String newMessage = event.getNewValue().toString();

        if (!oldMessage.equals(newMessage)) {
            Kweet entity = (Kweet) ((DataTable) event.getComponent()).getRowData();
            setKweetMessage(entity.getId(), newMessage);
        }
    }
}
