package nl.kb.dropwizard;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import nl.kb.dropwizard.endpoints.RootEndpoint;

public class App extends Application<Config> {

  public static void main(String[] args) throws Exception {
    new App().run(args);
  }

  @Override
  public void run(Config config, Environment environment) throws Exception {


    register(environment, new RootEndpoint());

  }

  private void register(Environment environment, Object component) {
    environment.jersey().register(component);
  }
}
