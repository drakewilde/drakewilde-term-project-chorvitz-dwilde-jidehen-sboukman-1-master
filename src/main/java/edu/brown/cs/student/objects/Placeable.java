package edu.brown.cs.student.objects;

import edu.brown.cs.student.algorithm.Coordinate;

/**
 * Generic placeable item on board.
 *
 */
public interface Placeable {

  /**
   * Updates a Placeable's coordinates.
   * @param col X value.
   * @param row Y value.
   */
  void updateCoordinate(Integer col, Integer row);

  /**
   * Gets the Placeable's coordinates.
   * @return List of Doubles representing coordinates on grid.
   */
  Coordinate getCoordinates();

  /**
   * Sets PLaceable's color.
   * @param color String representing color.
   */
  void setColor(String color);

  /**
   * Gets Placeable's color.
   * @return String representing color.
   */
  String getColor();


  /**
   * Gets Placeable's ID.
   * @return String representing unqiue ID.
   */
  Integer getId();

  /**
   * Gets Placeable's Dimension in a KDtree.
   * @return dimension as an integer value
   */
  Integer getDim();

  /**
   * Sets Placeable's Dimension in a KDtree.
   * @param inDim the intended dimension
   */
  void setDim(int inDim);

  /**
   * Returns the type of the placeable.
   * @return Type of placeable.
   */
  String getType();

}
