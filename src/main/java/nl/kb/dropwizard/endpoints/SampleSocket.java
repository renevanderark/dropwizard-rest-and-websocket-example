package nl.kb.dropwizard.endpoints;

import com.google.common.collect.Sets;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import java.io.IOException;
import java.util.Set;

import static nl.kb.dropwizard.util.JsonBuilder.jsn;
import static nl.kb.dropwizard.util.JsonBuilder.jsnO;

@WebSocket
public class SampleSocket  {

  Session session;

  @OnWebSocketConnect
  public void onConnect(Session session){
    this.session = session;
    Registrations.getInstance().add(this);
  }

  @OnWebSocketMessage
  public void onText(String msg) {
    broadcast(msg);
  }

  private void broadcast(String msg) {
    Registrations.getInstance().get().forEach(registration -> {
      try {
        if (registration.equals(this)) {
          registration.session.getRemote().sendString(getPayload(msg, "you"));
        } else {
          registration.session.getRemote().sendString(getPayload(msg, "someone else"));
        }
      } catch (IOException ignored) {
      }
    });
  }

  private String getPayload(String msg, String source) {
    return jsnO(
      "msg", jsn(msg),
      "source", jsn(source),
      "count", jsn(Registrations.getInstance().get().size())
    ).toString();
  }

  @OnWebSocketClose
  public void onClose(int statusCode, String reason) {
    Registrations.getInstance().remove(this);
    broadcast("logged off");
  }

  private static class Registrations {
    private static Registrations instance;
    private Set<SampleSocket> registrations = Sets.newConcurrentHashSet();

    static Registrations getInstance() {
      if (instance == null) {
        instance = new Registrations();
      }
      return instance;
    }

    private void add(SampleSocket socket) {
      registrations.add(socket);
    }

    private void remove(SampleSocket socket) {
      registrations.remove(socket);
    }

    private Set<SampleSocket> get() {
      return registrations;
    }
  }
}
