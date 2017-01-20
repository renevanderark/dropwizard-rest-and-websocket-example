package nl.kb.dropwizard.endpoints;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import java.io.IOException;

@WebSocket
public class SampleSocket {

  Session session;

  @OnWebSocketConnect
  public void onConnect(Session session){
    this.session = session;
    System.out.println("init "+session.getUpgradeRequest().getRequestURI().getPath() );
  }

  @OnWebSocketMessage
  public void onText(String msg) {
    System.out.println("rec "+msg );
    try {
      session.getRemote().sendString(msg);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @OnWebSocketClose
  public void onClose(int statusCode, String reason) {
    System.out.println("MyWebSocket.onClose()");
  }
}
