package edu.brown.cs.student.game;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edu.brown.cs.student.algorithm.Coordinate;
import edu.brown.cs.student.algorithm.KDcomparator;
import edu.brown.cs.student.algorithm.KDtree;
import edu.brown.cs.student.objects.GamePerson;
import edu.brown.cs.student.objects.Placeable;

/**
 * Test The KdTree's functionality
 * @author solomonboukman
 *
 */
public class TreeTest {
  private KDtree<Placeable> tree;
  private List<Placeable> people;

  /**
   * Sets up input data for tree and loads it
   */
  public void setUp() {
    people = new ArrayList<Placeable>();
    people.add(new GamePerson(1, "", 1, 1, false));
    people.add(new GamePerson(2, "", 2, 5, false));
    people.add(new GamePerson(3, "", -4, 7, false));
    people.add(new GamePerson(4, "", 8, 4, false));
    people.add(new GamePerson(5, "", 3, 5, false));
    people.add(new GamePerson(6, "", 2, 1, false));
    people.add(new GamePerson(7, "", 0, 6, false));
    people.add(new GamePerson(8, "", 4, 6, false));
    people.add(new GamePerson(9, "", 1, -5, false));

    KDcomparator<Placeable> comp = new KDcomparator<Placeable>();

    //load method implicitly tested
    tree = new KDtree<Placeable>(comp, people, -1, 2);
  }


  /**
   * test the search algorithm for the tree
   */
  @Test
  public void testNearest() {
    setUp();

    Coordinate coord1 = new Coordinate(6, 4);

    tree.setPos(coord1);

    tree.search(tree);

    Number closest1 = tree.getClosest().get(0).getId();

    assertEquals(closest1, 4);

    Coordinate coord2 = new Coordinate(3, 3);

    tree.setPos(coord2);

    tree.search(tree);

    Number closest2 = tree.getClosest().get(0).getId();

    assertEquals(closest2, 5);

    Coordinate coord3 = new Coordinate(0, 0);

    tree.setPos(coord3);

    tree.search(tree);

    Number closest3 = tree.getClosest().get(0).getId();

    assertEquals(closest3, 1);

    Coordinate coord4 = new Coordinate(-5, 5);

    tree.setPos(coord4);

    tree.search(tree);

    Number closest4 = tree.getClosest().get(0).getId();

    assertEquals(closest4, 3);

    Coordinate coord5 = new Coordinate(-2, -3);

    tree.setPos(coord5);

    tree.search(tree);

    Number closest5 = tree.getClosest().get(0).getId();

    assertEquals(closest5, 9);





  }


}