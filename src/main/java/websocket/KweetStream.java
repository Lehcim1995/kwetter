package websocket;


import javax.inject.Inject;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.logging.Level;
import java.util.logging.Logger;

//@ApplicationScoped // Make stateless
@ServerEndpoint( value = "/kweetstream")
public class KweetStream
{
    @Inject
    KweetStreamHandler kweetStreamHandler;

    @OnOpen
    public void open(Session session)
    {
        System.out.println("Opened " + session.getId());
        Logger.getAnonymousLogger().log(Level.ALL,"Opened " + session.getId());
        kweetStreamHandler.addSession(session);
    }

    @OnClose
    public void close(Session session)
    {
        System.out.println("Closed " + session.getId());
        Logger.getAnonymousLogger().log(Level.ALL,"Closed " + session.getId());
        kweetStreamHandler.removeSession(session);
    }

    @OnError
    public void onError(Throwable error)
    {
        Logger.getAnonymousLogger().log(Level.ALL,"Error " + error.getMessage());
    }

    @OnMessage
    public void handleMessage(String message, Session session)
    {

        System.out.println("Message : " +  message);
        Logger.getAnonymousLogger().log(Level.ALL,"Message : " +  message);

        if (message.equals("get"))
        {
            kweetStreamHandler.updateKweetsForAll();
        }

        if (message.equals("hallo"))
        {
            System.out.println("sending hallo");
            kweetStreamHandler.sendHallo();
        }

        // TODO parse message

        if (message.equals("createdkweet"))
        {

        }
    }
}
