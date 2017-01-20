package nl.kb.dropwizard.endpoints;

import com.google.common.collect.Sets;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import java.io.IOException;
import java.util.Set;

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
    Registrations.getInstance().get().forEach(registration -> {
      try {
        registration.session.getRemote().sendString(msg);
      } catch (IOException ignored) {
      }
    });
  }

  @OnWebSocketClose
  public void onClose(int statusCode, String reason) {
    Registrations.getInstance().remove(this);
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
