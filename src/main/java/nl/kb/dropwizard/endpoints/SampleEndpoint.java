package nl.kb.dropwizard.endpoints;

import org.glassfish.jersey.server.ChunkedOutput;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.io.IOException;
import java.util.Date;

import static nl.kb.dropwizard.util.JsonBuilder.jsn;
import static nl.kb.dropwizard.util.JsonBuilder.jsnO;

@Path("/sample")
public class SampleEndpoint {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response index() {

    return Response.ok(jsnO("sample", jsn(new Date().toString()))).build();
  }

  @POST
  @Produces(MediaType.APPLICATION_OCTET_STREAM)
  public Response sampleTask() {
    final ChunkedOutput<String> output = executeSampleTask();

    return Response.ok(output).build();
  }

  private ChunkedOutput<String> executeSampleTask() {
    final ChunkedOutput<String> output = new ChunkedOutput<>(String.class);

    new Thread(() -> {
      for (int i = 0; i < 100; i++) {
        try {
          output.write("\n" + jsnO("progress", jsn(i + 1)).toString());
          Thread.sleep(20L);
        } catch (IOException | InterruptedException ignored) {
        }
      }
      try {
        output.close();
      } catch (IOException ignored) {

      }
    }).start();

    return output;
  }
}
