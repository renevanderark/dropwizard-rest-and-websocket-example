package nl.kb.dropwizard.endpoints;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class RootEndpoint {

  @GET
  @Produces(MediaType.TEXT_HTML)
  public Response getHomepage() {

    return Response.ok("<html><body>welcome</body></html>").build();
  }
}
