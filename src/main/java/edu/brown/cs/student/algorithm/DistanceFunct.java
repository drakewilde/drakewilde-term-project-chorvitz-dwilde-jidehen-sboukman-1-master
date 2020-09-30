package edu.brown.cs.student.algorithm;

import edu.brown.cs.student.game.Edge;
import edu.brown.cs.student.game.Node;

/**
 * This interface is used to specify the type of distance function that can be
 * passed into the Astar algorithm.
 *
 * @param <N> class that implements Node
 * @param <E> class that implements Edge
 */
public interface DistanceFunct<N extends Node<E>, E extends Edge<N, E>> {

  /**
   * Gets a distance from input person to a specified node or point.
   *
   * @param node the node you are taking distance from
   * @return the distance from to the specified person
   */
  Double dist(N node);

}
