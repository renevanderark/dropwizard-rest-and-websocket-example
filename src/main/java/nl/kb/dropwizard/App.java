package nl.kb.dropwizard;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import nl.kb.dropwizard.db.ExperimentalDAO;
import nl.kb.dropwizard.endpoints.RootEndpoint;
import nl.kb.dropwizard.endpoints.SampleEndpoint;
import nl.kb.dropwizard.endpoints.SampleWebSocketServlet;
import org.skife.jdbi.v2.DBI;

import javax.servlet.Servlet;

public class App extends Application<Config> {

  public static void main(String[] args) throws Exception {
    new App().run(args);
  }

  @Override
  public void initialize(Bootstrap<Config> bootstrap) {
    bootstrap.addBundle(new AssetsBundle("/assets", "/assets"));

    bootstrap.setConfigurationSourceProvider(
      new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(),
        new EnvironmentVariableSubstitutor(false))
    );
  }

  @Override
  public void run(Config config, Environment environment) throws Exception {
    final DBIFactory factory = new DBIFactory();
    final DBI jdbi = factory.build(environment, config.getDataSourceFactory(), "hsql");
    final ExperimentalDAO dao = jdbi.onDemand(ExperimentalDAO.class);
    dao.dropSomething();
    dao.createSomethingTable();

    register(environment, new SampleEndpoint(dao));
    register(environment, new RootEndpoint(config.getAppTitle(), config.getHostName(), config.getWsProtocol()));
    registerServlet(environment, new SampleWebSocketServlet());
  }

  private void registerServlet(Environment environment, Servlet servlet) {
    environment.servlets().addServlet("sampleWebsocket", servlet).addMapping("/socket-sample");
  }

  private void register(Environment environment, Object component) {
    environment.jersey().register(component);
  }
}
