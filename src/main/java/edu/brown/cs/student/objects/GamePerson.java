package edu.brown.cs.student.objects;

import edu.brown.cs.student.algorithm.Coordinate;

/**
 * A Person in the game.
 *
 */
public class GamePerson implements Placeable {

  private String color, type = "Person";
  private Integer id, row, col;
  private Boolean isInfected;
  private Coordinate coordinate;
  private int dim; //for KDtree
  private boolean targeted;

  /**
   * Creates an instance of a person in the game.
   * @param i Id.
   * @param c Color.
   * @param cl Column value.
   * @param rw Row value.
   * @param inf Infected status.
   */
  public GamePerson(Integer i, String c, Integer cl, Integer rw, Boolean inf) {
    this.id = i;
    this.color = c;
    this.isInfected = inf;

    this.coordinate = new Coordinate(cl, rw);
    this.row = rw;
    this.col = cl;
    this.isInfected = false;
  }



  @Override
  public void updateCoordinate(Integer col, Integer row) {
    this.row = row;
    this.col = col;
    this.coordinate = new Coordinate(col, row);
  }

  @Override
  public Coordinate getCoordinates() {
    return this.coordinate;
  }

  @Override
  public void setColor(String color) {
    this.color = color;
  }

  @Override
  public String getColor() {
    return this.color;
  }

  @Override
  public Integer getId() {
    return this.id;
  }

  /**
   * Sets infected boolean of Person.
   * @param b true or false.
   */
  public void setInfected(Boolean b) {
    this.isInfected = b;
  }


  /**
   * Gets the infected status of Person.
   * @return true or false.
   */
  public Boolean getInfected() {
    return this.isInfected;
  }

  @Override
  public Integer getDim() {
    return dim;
  }

  @Override
  public void setDim(int inDim) {
    dim = inDim;
  }

  @Override
  public String getType() {
    return this.type;

  }
}
