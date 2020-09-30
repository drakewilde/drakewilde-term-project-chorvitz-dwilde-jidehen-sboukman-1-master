package edu.brown.cs.student.repl;

import edu.brown.cs.student.game.Game;
import edu.brown.cs.student.socket.WebSocketHandler;
import spark.Spark;

/**
 * Main class to start program.
 *
 */
public final class Main {
  private String[] args;
  private static final String filepath = "data/objects2.sqlite3";

  /**
   * The initial method called when execution begins.
   *
   * @param args An array of command line arguments
   */

  public static void main(String[] args) {
    new Main(args).run();

  }

  private Main(String[] a) {
    this.args = a;
  }

  private void run() {
    Game game = new Game(filepath);
    Spark.port(4567);
    Spark.staticFileLocation("/src/main/resources/static");
    Spark.webSocket("/game", new WebSocketHandler(game));
    Spark.init();
  }
}
