package websocket;

import classes.Kweet;
import dao.JPA;
import interfaces.KweetDao;
import services.JsonService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.websocket.Session;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped // Make stateless??
public class KweetStreamHandler
{
    private final Set<Session> sessions = new HashSet<>();
    private final Set<Kweet> kweets = new HashSet<>(); // TODO probably kweets

    @Inject
    JsonService jsonService;

    @Inject
    @JPA
    KweetDao kweetDao;

    public void addSession(Session session)
    {
        sessions.add(session);
    }

    public void removeSession(Session session)
    {
        sessions.remove(session);
    }

    public void updateKweetsForAll()
    {
        sendToAllConnectedSessions(jsonService.parse(kweetDao.getKweets()));
    }

    public void sendHallo() {
        sendToAllConnectedSessions("hallo");
    }

    private void sendToAllConnectedSessions(JsonObject message)
    {
        sessions.forEach(session -> sendToSession(session, message));
    }

    private void sendToAllConnectedSessions(String message)
    {
        sessions.forEach(session -> sendToSession(session, message));
    }

    private void sendToSession(Session session, String message)
    {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException ex) {
            sessions.remove(session);
            Logger.getLogger(KweetStreamHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void sendToSession(Session session, JsonObject message)
    {
        sendToSession(session, message.toString());
    }
}
