package nl.kb.dropwizard;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

class Config extends Configuration {

  @JsonProperty
  private String appTitle = "";
  @JsonProperty
  private String hostName = "";
  @JsonProperty
  private String wsProtocol = "ws";


  String getAppTitle() {
    return appTitle;
  }

  String getHostName() {
    return hostName;
  }

  String getWsProtocol() {
    return wsProtocol;
  }
}
