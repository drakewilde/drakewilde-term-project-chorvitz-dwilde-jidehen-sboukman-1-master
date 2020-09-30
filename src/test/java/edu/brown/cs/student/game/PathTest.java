package edu.brown.cs.student.game;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import edu.brown.cs.student.algorithm.AStar;
import edu.brown.cs.student.algorithm.Hueristic;
import edu.brown.cs.student.objects.GamePerson;
import edu.brown.cs.student.objects.Placeable;

public class PathTest {

  private AStar<GameNode, GameEdge, Hueristic> pathfinder;
  private Board board;

  public void setUp() {

    board = new Board(5, new GamePerson(1,"", 1, 1, false), new ArrayList<Placeable>());
    GameNode target = board.getArray()[0][4];
    pathfinder = new AStar<GameNode, GameEdge, Hueristic>(new Hueristic(target));

  }

  /**
   * Tests path with hueristic set as top right node of board
   */
  @Test
  public void testPath1() {
    setUp();
    GameNode src1 = board.getArray()[0][0];
    ArrayList<GameEdge> path1 = pathfinder.search(src1, 4);
    assertEquals(path1.size(), 4);

    GameNode src2 = board.getArray()[4][0];
    ArrayList<GameEdge> path2 = pathfinder.search(src2, 4);
    assertEquals(path2.size(), 8);

    GameNode src3 = board.getArray()[0][3];
    ArrayList<GameEdge> path3 = pathfinder.search(src3, 4);
    assertEquals(path3.size(), 1);

    GameNode src4 = board.getArray()[2][3];
    ArrayList<GameEdge> path4 = pathfinder.search(src3, -1);
    assertEquals(path4.size(), 0);



  }

  /**
   * Tests path with hueristic set middle node of board
   */
  @Test
  public void testPath2() {
    setUp();
    GameNode huer = board.getArray()[2][3];
    pathfinder.setDistFunct(new Hueristic(huer));

    GameNode src1 = board.getArray()[2][3];
    ArrayList<GameEdge> path1 = pathfinder.search(src1, 12);
    assertEquals(path1.size(), 1);

    GameNode src2 = board.getArray()[0][0];
    ArrayList<GameEdge> path2 = pathfinder.search(src2, -1);
    assertEquals(path2.size(), 0);

    GameNode src3 = board.getArray()[0][0];
    ArrayList<GameEdge> path3 = pathfinder.search(src3, 12);
    assertEquals(path3.size(), 4);

    ArrayList<Number> path3Ids = new ArrayList<Number>();

    for(int i = 0; i < path3.size(); i++) {
      if(i == 0) {
        GameEdge first = path3.get(i);
        path3Ids.add(first.getSource().getId());
        path3Ids.add(first.getDestination().getId());

      }else {
        GameEdge rest = path3.get(i);
        path3Ids.add(rest.getDestination().getId());
      }

    }

    assertEquals(path3Ids.get(0), 0);
    assertEquals(path3Ids.get(1), 1);
    assertEquals(path3Ids.get(2), 6);
    assertEquals(path3Ids.get(3), 7);





  }


}
