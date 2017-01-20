package nl.kb.dropwizard;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

class Config extends Configuration {

  @JsonProperty
  private String appTitle = "";
  @JsonProperty
  private String hostName = "";


  String getAppTitle() {
    return appTitle;
  }

  String getHostName() {
    return hostName;
  }
}
