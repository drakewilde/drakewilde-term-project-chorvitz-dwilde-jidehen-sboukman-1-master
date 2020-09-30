package edu.brown.cs.student.socket;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketError;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import edu.brown.cs.student.game.Game;
import edu.brown.cs.student.objects.Placeable;

/**
 * Allows for communication between front and back end of program.
 *
 */

@WebSocket
public class WebSocketHandler {

  private static final Queue<Session> SESSIONS = new ConcurrentLinkedQueue<>();
  private static Session currSession;
  private static final Gson GSON = new Gson();
  private Game game;



  /**
   * Setup the game instance.
   * @param game infection game
   */
  public WebSocketHandler(Game game) {
    this.game = game;
  }


  /**
   * Handle new connection.
   * @param session of new player
   */
  @OnWebSocketConnect
  public void connected(Session session) {
    currSession = session;
    System.out
    .println("Welcome " + session.getLocalAddress() + " " + session.getRemote().toString());
    game.refreshLevel();
    this.broadcastMessage(GSON.toJson(game));
  }


  /**
   * Handle leave session.
   * @param session session that is left
   * @param statusCode status
   * @param reason reason for leave
   */
  @OnWebSocketClose
  public void closed(Session session, int statusCode, String reason) {
    this.broadcastMessage(GSON.toJson("close"));
    SESSIONS.remove(session);
  }

  /**
   * Handle what to do when a message is received. This is bulk of socket functionality.
   * The specific action is determined by the 'type' attribute of the received json object.
   * @param session session to send to
   * @param message message received
   * @throws IOException if error occurs in getting input or sending output
   */
  @OnWebSocketMessage
  public void message(Session session, String message) throws IOException {
    JsonObject jsonObject = GSON.fromJson(message, JsonObject.class);
    if (jsonObject.get("type") != null) {
      if (jsonObject.get("type").getAsString().equals("position")) {
        JsonObject placeablesObject = jsonObject.get("placeables").getAsJsonObject();
        for (String k : placeablesObject.keySet()) {
          Placeable curr = this.game.getLevel().getBoard().getPlaceableMap().get(Integer.parseInt(k));
          int newCol = placeablesObject.get(k).getAsJsonObject().get("col").getAsInt();
          int newRow = placeablesObject.get(k).getAsJsonObject().get("row").getAsInt();
          curr.updateCoordinate(newCol, newRow);
        }
        this.game.getLevel().getBoard().updateBoard();
      } else if (jsonObject.get("type").getAsString().equals("next")) {
        this.game.incrementLevel();
      } else if (jsonObject.get("type").getAsString().equals("reset")) {
        this.game.resetLevel();
      } else if (jsonObject.get("type").getAsString().equals("update")) {
        this.game.getLevel().getBoard().updateBoard();
      }
      else if (jsonObject.get("type").getAsString().equals("set_level")) {
        int level = jsonObject.get("level").getAsInt();
        this.game.setLevel(level);
      }
      this.broadcastMessage(GSON.toJson(game));
    }
  }

  /**
   * Handle error catching if an error occurs in the websocket.
   * @param session an error happens from
   * @param e error that occurs
   * @throws Exception websocket exception
   */
  @OnWebSocketError
  public void thowError(Session session, Throwable e) throws Exception {
    System.out.println(e.getMessage());
    e.printStackTrace();
  }

  /**
   * Sends a message to the front end.
   * @param msg sent to front end
   */
  public void broadcastMessage(String msg) {
    try {
      currSession.getRemote().sendString(msg);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

  }
}
