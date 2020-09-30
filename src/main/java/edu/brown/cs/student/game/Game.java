package edu.brown.cs.student.game;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.brown.cs.student.data.DatabaseException;
import edu.brown.cs.student.data.LevelsQuerier;

/**
 * Contains the logic for running the game.
 *
 */
public class Game {
  private Level level;
  private List<Level> levels;
  private static final int MAX_LEVEL = 5;
  private Integer currIndex;
  private transient String path;
  private transient Connection conn;
  private transient LevelsQuerier proxy;

  /**
   * Sets up the game and initializes variables.
   * @param filepath Path to database needed to setup connection.
   */
  public Game(String filepath)  {
    this.path = filepath;
    this.levels = new ArrayList<>();
    this.currIndex = 0;
    try {
      this.setupConnection();
      for (int i = 0; i <= MAX_LEVEL; i++) {
        Level currLevel = proxy.query(i);
        this.levels.add(currLevel);
      }
      this.level = this.levels.get(this.currIndex);
    } catch (DatabaseException e) {
      e.printStackTrace();
    }
  }

  /**
   * Sets up a connection to the database holding game data.
   */
  public void setupConnection() {
    try {
      Class.forName("org.sqlite.JDBC");
      String urlToDB = "jdbc:sqlite:" + this.path;
      this.conn = DriverManager.getConnection(urlToDB);
      Statement stat = conn.createStatement();
      stat.executeUpdate("PRAGMA foreign_keys=ON;");
      this.proxy = new LevelsQuerier(conn);
    } catch (SQLException | ClassNotFoundException  e) {
      e.printStackTrace();
    }
  }

  /**
   * Resets all levels to original states.
   */
  public void resetLevels() {
    this.currIndex = 0;
    this.levels.clear();
    try {
      for (int i = 0; i <= MAX_LEVEL; i++) {
        Level currLevel = this.proxy.query(i);
        this.levels.add(currLevel);
      }
      this.level = this.levels.get(this.currIndex);
    } catch (DatabaseException e) {
      e.printStackTrace();
    }

  }

  /**
   * Used to refresh the page.
   */
  public void refreshLevel() {
    try {
      this.level = this.proxy.query(this.currIndex);
    } catch (DatabaseException e) {
      e.printStackTrace();
    }
  }

  /**
   * Resets an individual level.
   */
  public void resetLevel() {
    try {
      if (this.currIndex != 0) {
        this.currIndex--;
        this.level = this.proxy.query(this.currIndex);
      } else {
        this.currIndex = this.levels.size() - 1;
        this.level = this.proxy.query(this.levels.size() - 1);
      }
    } catch (DatabaseException e) {
      e.printStackTrace();
    }
  }

  public void setLevel (int i) {
    try {
      this.resetLevels();
      this.level = this.proxy.query(i);
      this.currIndex = i;
    } catch (DatabaseException e) {
      e.printStackTrace();
    }
  }

  /**
   * Gets a Level.
   * @return an instance of Level.
   */
  public Level getLevel() {
    return this.level;
  }

  /**
   * Increments to the next level.
   */
  public void incrementLevel() {
    if (currIndex < MAX_LEVEL) {
      this.refreshLevel();
      this.currIndex++;
      this.level = this.levels.get(currIndex);
    } else {
      this.resetLevels();
    }
  }
}
