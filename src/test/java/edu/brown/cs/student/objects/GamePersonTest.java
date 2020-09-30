package edu.brown.cs.student.objects;

import org.junit.Test;

import edu.brown.cs.student.algorithm.Coordinate;

public class GamePersonTest {

  @Test
  public void test() {
    GamePerson gp = new GamePerson(0, "blue", 0, 0, false);
    gp.setDim(2);
    assert (gp.getColor().contentEquals("blue"));
    assert (gp.getId() == 0);
    assert (gp.getInfected() == false);
    assert (gp.getDim().equals(2));
    assert (gp.getCoordinates().equals(new Coordinate(0,0)));

    gp.setInfected(true);
    gp.setColor("red");
    assert (gp.getInfected());
    assert(gp.getColor().equals("red"));

    gp.updateCoordinate(1, 1);
    assert (gp.getCoordinates().equals(new Coordinate(1,1)));

    assert(gp.getType().contentEquals("Person"));

  }

}
