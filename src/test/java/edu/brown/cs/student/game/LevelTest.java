package edu.brown.cs.student.game;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import edu.brown.cs.student.objects.GamePerson;
import edu.brown.cs.student.objects.Placeable;

public class LevelTest {


  @Test
  public void test() {
    List<Placeable> placeablesList = Arrays.asList(new GamePerson (0, "blue", 0, 0, false), new GamePerson (1, "blue", 1, 1, false));
    Level level = new Level(2, 100., placeablesList.get(0), 1, placeablesList, 0);
    assert (level.getDimension() == 2);
    assert (level.getPlaceables().equals(placeablesList));
  }

}
