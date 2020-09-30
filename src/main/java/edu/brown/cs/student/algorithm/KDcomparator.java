package edu.brown.cs.student.algorithm;

import java.util.Comparator;

import edu.brown.cs.student.objects.Placeable;

/**
 * Used to sort a list of nodes in a queue when implementing kd search.
 *
 * @param <P> class that extends Placeable.
 */
public class KDcomparator<P extends Placeable> implements Comparator<P> {

  private int dim;

  /**
   * Compares two nodes based on their coordinate distance from one another.
   */
  @Override
  public int compare(P person1, P person2) {
    double valOne = person1.getCoordinates().asList().get(dim).doubleValue();
    double valTwo = person2.getCoordinates().asList().get(dim).doubleValue();
    return Double.compare(valOne, valTwo);
  }

  /**
   * Sets the dimension of the comparator.
   *
   * @param d the dimension we'd like to compare to at the moment
   */
  public void setDimension(int d) {
    dim = d;
  }

  /**
   * Gets the dimension of the comparator.
   *
   * @return the dimension of the comparator
   */
  public int getDim() {
    return dim;
  }

}
