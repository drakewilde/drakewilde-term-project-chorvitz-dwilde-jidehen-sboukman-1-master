package edu.brown.cs.student.objects;

import org.junit.Test;

import edu.brown.cs.student.algorithm.Coordinate;

public class BarrierTest {

  @Test
  public void test() {
    Barrier b = new Barrier(0, 1, 1);
    assert(b.getId().equals(0));
    b.updateCoordinate(1, 2);
    assert (b.getCoordinates().equals(new Coordinate(1,2)));
    assert(b.getType().contentEquals("Barrier"));
  }

}
