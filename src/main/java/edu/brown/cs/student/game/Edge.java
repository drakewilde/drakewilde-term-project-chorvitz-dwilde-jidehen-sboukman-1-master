package edu.brown.cs.student.game;

/**
 * Edge between two Nodes.
 *
 * @param <N> class that implements Node.
 * @param <E> class that implements Edge.
 */
public interface Edge<N extends Node<E>, E extends Edge<N, E>> {

  /**
   * Gets the source Node of Edge.
   * @return The source Node of Edge.
   */
  N getSource();

  /**
   * Gets the destination Node of Edge.
   * @return The destination Node of Edge.
   */
  N getDestination();

  /**
   * Gets the Edge's weight.
   * @return Double representing the Edge's weight.
   */
  Double getWeight();

  /**
   * Sets traversable Boolean to true or false.
   * @param t True or False boolean.
   */
  void setTraversable(Boolean t);

  /**
   * Returns true if Edge is traversable and false otherwise.
   * @return True or False.
   */
  Boolean isTraversable();

}
