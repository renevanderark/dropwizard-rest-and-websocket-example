package nl.kb.dropwizard;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;


class Config extends Configuration {

  @JsonProperty
  private String appTitle = "";
  @JsonProperty
  private String hostName = "";
  @JsonProperty
  private String wsProtocol = "ws";

  private DataSourceFactory database;


  String getAppTitle() {
    return appTitle;
  }

  String getHostName() {
    return hostName;
  }

  String getWsProtocol() {
    return wsProtocol;
  }


  @JsonProperty("database")
  DataSourceFactory getDataSourceFactory() {
    return database;
  }

  @JsonProperty("database")
  void setDataSourceFactory(DataSourceFactory dataSourceFactory) {
    this.database = dataSourceFactory;
  }
}
