package nl.kb.dropwizard.endpoints;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

public class SampleWebSocketServlet extends WebSocketServlet {
  @Override
  public void configure(WebSocketServletFactory factory) {
    factory.getPolicy().setIdleTimeout(10000L);

    factory.register(SampleSocket.class);
  }

}
