package nl.kb.dropwizard.db;

import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseHelper {

  private final DBI dbi;

  public DatabaseHelper(DBI dbi) {

    this.dbi = dbi;
  }

  public void createTableIfNotExist(String tableName, String createTableSql) {
    try {
      DatabaseMetaData dbm = dbi.open().getConnection().getMetaData();
      ResultSet tables = dbm.getTables(null, null, tableName, null);
      if (!tables.next()) {
        Handle handle = dbi.open();
        handle.execute(createTableSql);
        handle.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
