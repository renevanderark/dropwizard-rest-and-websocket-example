package nl.kb.dropwizard.db;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

import java.util.List;

public interface ExperimentalDAO {

  @SqlUpdate("drop table if exists something")
  void dropSomething();

  @SqlUpdate("create table something (id int identity primary key, name varchar(100))")
  void createSomethingTable();

  @SqlUpdate("insert into something (name) values (:name)")
  @GetGeneratedKeys
  Long insertSomething(@Bind("name") String name);

  @SqlQuery("select name from something")
  List<String> list();


}
