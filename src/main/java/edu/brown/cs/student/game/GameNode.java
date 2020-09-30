package edu.brown.cs.student.game;

import java.util.ArrayList;
import java.util.List;

import edu.brown.cs.student.algorithm.Coordinate;

/**
 * Instance of a node on the game board.
 *
 */
public class GameNode implements Node<GameEdge> {

  private Number id;
  private List<GameEdge> edges;
  private Coordinate coordinate;
  private Integer row, col;

  /**
   * Creates a node to be places on the game board.
   * @param x Column value
   * @param y Row value
   * @param i Id
   */
  public GameNode(Integer x, Integer y, int i) {
    this.col = x;
    this.row = y;
    this.coordinate = new Coordinate(x, y);
    this.id = i;
    this.edges = new ArrayList<GameEdge>();

  }

  @Override
  public Coordinate getCoordinate() {
    return this.coordinate;
  }

  @Override
  public Number getId() {
    return this.id;
  }

  @Override
  public void addEdge(GameEdge edge) {
    this.edges.add(edge);
  }

  @Override
  public List<GameEdge> getEdges() {
    return this.edges;
  }

}
