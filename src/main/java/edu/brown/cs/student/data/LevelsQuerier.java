package edu.brown.cs.student.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import edu.brown.cs.student.game.Level;
import edu.brown.cs.student.objects.Barrier;
import edu.brown.cs.student.objects.GamePerson;
import edu.brown.cs.student.objects.Placeable;

/**
 * Implementation of a database proxy.
 *
 */
public class LevelsQuerier {

  private Connection conn;
  private static final String COLOR = "#34A4FF";
  private Integer dim, numMoves;
  private Double percent;
  private Placeable starter;
  private List<Placeable> placeables;

  /**
   * Initializes the connection variable.
   * @param c Connection to database.
   */
  public LevelsQuerier(Connection c) {
    this.conn = c;
  }


  /**
   * Queries database for attributes of a level.
   * @param levelId Id of level to query.
   * @return an instance of Level.
   * @throws DatabaseException If query fails.
   */
  public Level query(Integer levelId) throws DatabaseException {
    this.placeables = new ArrayList<>();
    PreparedStatement prep;
    String query = "SELECT objects.id, objects.column, objects.row, objects.type,"
        + " levels.dimension, levels.start_node, levels.num_moves, levels.percent FROM "
        + "objects JOIN levels ON levels.id = objects.level_id WHERE levels.id = ?";
    try {
      prep = conn.prepareStatement(query);
      prep.setInt(1, levelId);
      ResultSet rs = prep.executeQuery();
      while (rs.next()) {
        Integer id = rs.getInt(1);
        Integer col = rs.getInt(2);
        Integer row = rs.getInt(3);
        String placeableType = rs.getString(4);
        this.dim = rs.getInt(5);
        this.numMoves = rs.getInt(7);
        this.percent = rs.getDouble(8);
        if (placeableType.equals("Person")) {
          Placeable person = new GamePerson(id, COLOR, col, row, false);
          if (id.equals(rs.getInt(6))) {
            this.starter = person;
          }
          this.placeables.add(person);
        }
        if (placeableType.equals("Barrier")) {
          Placeable barrier = new Barrier(id, col, row);
          this.placeables.add(barrier);
        }
      }
      Level level = new Level(dim, this.percent, this.starter, numMoves, placeables, levelId);
      return level;
    } catch (Exception e) {
      e.printStackTrace();
      throw new DatabaseException(e.getMessage());
    }
  }
}
