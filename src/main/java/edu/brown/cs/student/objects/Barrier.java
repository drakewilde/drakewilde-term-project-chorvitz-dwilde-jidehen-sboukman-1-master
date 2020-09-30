package edu.brown.cs.student.objects;

import edu.brown.cs.student.algorithm.Coordinate;

/**
 * A barrier on the board.
 *
 */
public class Barrier implements Placeable {

  private Integer id, row, col;
  private Coordinate coordinate;
  private int dim; //for KDtree
  private String type = "Barrier";

  /**
   * Creates instance of barrier on the board.
   * @param i Id.
   * @param c Column value.
   * @param r Row value.
   */
  public Barrier(Integer i, Integer c, Integer r) {
    this.id = i;
    this.coordinate = new Coordinate(c, r);
    this.row = r;
    this.col = c;
    this.type = "Barrier";
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
  }

  @Override
  public String getColor() {
    return null;
  }

  @Override
  public Integer getId() {
    return this.id;
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
