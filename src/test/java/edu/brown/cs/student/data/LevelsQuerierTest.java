package edu.brown.cs.student.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.junit.Test;

public class LevelsQuerierTest {

  private LevelsQuerier querier;

  /**
   * Sets up a connection and an instance of the database proxy.
   *
   * @param file The path to the database file.
   */
  public void setup(String filepath) {
    try {
      Class.forName("org.sqlite.JDBC");
      String urlToDB = "jdbc:sqlite:" + filepath;
      Connection conn = DriverManager.getConnection(urlToDB);
      Statement stat = conn.createStatement();
      stat.executeUpdate("PRAGMA foreign_keys=ON;");
      this.querier = new LevelsQuerier(conn);
    } catch (Exception e) {
      System.err.println("ERROR: could not make connection");
    }
  }

  /**
   * Resets the database and mdb connection variables
   */
  public void reset() {
    this.querier = null;
  }

  /**
   * Tests that a query returns the correct number of records.
   *
   * @throws Exception
   */
  @Test
  public void recordTest() {
  }

  /**
   * Tests that an error is thrown when query is called on a fake level.
   */
  @Test
  public void fakeLevelTest() {

  }

  /**
   * Tests that the get connection method works.
   */
  @Test
  public void connectionTest() {
  }

  /**
   * Tests that creating a list from the levels returned works correctly.
   */
  @Test
  public void getNodesTest() {
  }

  /**
   * Tests that an error is thrown when sql table does not have the correct format.
   */
  @Test
  public void badSqlTest() {

  }

  /**
   * Tests that an error is thrown when the placeables have null values.
   */
  @Test
  public void badNodeTest() {

  }
}

