package edu.brown.cs.student.game;

import java.util.List;

import edu.brown.cs.student.algorithm.Coordinate;

/**
 * A generic node on the board.
 *
 * @param <E> class that implements Edge
 */
public interface Node<E extends Edge> {

  /**
   * Gets the Node's coordinates.
   * @return List of Doubles representing coordinates on grid.
   */
  Coordinate getCoordinate();

  /**
   * Gets the Node's unique ID.
   * @return String representing Node's unique ID.
   */
  Number getId();

  /**
   * Adds a new edge to Node's list of edges.
   * @param edge Edge to be added.
   */
  void addEdge(E edge);

  /**
   * Gets the Edges connected to the Node.
   *
   * @return List of edges.
   */
  List<E> getEdges();

}
