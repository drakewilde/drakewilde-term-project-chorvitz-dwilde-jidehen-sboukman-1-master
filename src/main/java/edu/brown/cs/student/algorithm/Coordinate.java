package edu.brown.cs.student.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Class represents a Node's Coordinates.
 */
public class Coordinate {

  private List<Integer> coordinates;
  private Integer xVal, yVal;

  /**
   * Constructor of coordinate instantiates coordinates values.
   *
   * @param x Column value.
   * @param y Row value.
   */
  public Coordinate(Integer x, Integer y) {
    this.xVal = x;
    this.yVal = y;
    this.coordinates = new ArrayList<>();
    this.coordinates.add(xVal);
    this.coordinates.add(yVal);
  }

  /**
   * Gets the coordinate's X value.
   *
   * @return X coordinate
   */
  public Integer getX() {
    return this.xVal;
  }

  /**
   * Gets the coordinate's Y value.
   *
   * @return Y coordinate
   */
  public Integer getY() {
    return this.yVal;
  }

  /**
   * Gets the list of coordinates.
   *
   * @return The list of coordinates
   */
  public List<Integer> asList() {
    return this.coordinates;
  }

  @Override
  public String toString() {
    return (this.xVal + ", " + this.yVal);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }

    if (Coordinate.class.isAssignableFrom(obj.getClass())) {
      Coordinate c = (Coordinate) obj;
      if (this.xVal == null || this.yVal == null || c.xVal == null || c.yVal == null) {
        return false;
      }

      if (this.xVal.equals(c.xVal) && this.yVal.equals(c.yVal)) {
        return true;
      }
    }

    return false;
  }


}
