package nl.kb.dropwizard;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

class Config extends Configuration {

  @JsonProperty
  private String appTitle = "";


  String getAppTitle() {
    return appTitle;
  }
}
