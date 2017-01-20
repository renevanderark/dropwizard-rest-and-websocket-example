package nl.kb.dropwizard;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import nl.kb.dropwizard.endpoints.RootEndpoint;
import nl.kb.dropwizard.endpoints.SampleEndpoint;
import nl.kb.dropwizard.endpoints.SampleWebSocketServlet;

import javax.servlet.Servlet;

public class App extends Application<Config> {

  public static void main(String[] args) throws Exception {
    new App().run(args);
  }

  @Override
  public void initialize(Bootstrap<Config> bootstrap) {
    bootstrap.addBundle(new AssetsBundle("/assets", "/assets"));
  }

  @Override
  public void run(Config config, Environment environment) throws Exception {


    register(environment, new SampleEndpoint());
    register(environment, new RootEndpoint(config.getAppTitle()));
    registerServlet(environment, new SampleWebSocketServlet());
  }

  private void registerServlet(Environment environment, Servlet servlet) {
    environment.servlets().addServlet("sampleWebsocket", servlet).addMapping("/socket-sample");
  }

  private void register(Environment environment, Object component) {
    environment.jersey().register(component);
  }
}
