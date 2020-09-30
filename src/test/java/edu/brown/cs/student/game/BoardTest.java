package edu.brown.cs.student.game;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edu.brown.cs.student.objects.GamePerson;
import edu.brown.cs.student.objects.Placeable;

public class BoardTest {

  private Board board;

  public void setup(int dim, int numPlaceables) {
    List<Placeable> placeables = new ArrayList<Placeable>();
    int currCol = 0;
    int currRow = 0;
    Placeable start = null;
    for (int i = 0; i < numPlaceables; i++) {
      Placeable p = new GamePerson(i, null, currCol, currRow, false);
      placeables.add(p);
      start = p;
      currCol++;
      currRow++;
    }

    this.board = new Board(dim, start, placeables);
  }

  /**
   * Tests that the board is created with the correct dimensions and the node array is the correct length.
   */
  @Test
  public void dimtest() {
    this.setup(5, 5);
    assertEquals(this.board.getArray().length, 5);
    assertEquals(this.board.getArray()[0].length, 5);
  }


  /**
   * Tests that the number of placeables on the board is the correct size.
   * Also tests that the loadObjects method works.
   */
  @Test
  public void placeableTest() {
    this.setup(10, 6);
    assertEquals(this.board.getPlaceableMap().size(), 6);
    this.setup(10, 10);
    assertEquals(this.board.getPlaceableMap().size(), 10);
  }

  /**
   * Tests that board creation does not throw an error if the board is 0x0.
   */
  @Test
  public void zeroDimTest() {
    this.setup(0, 0);
    assertEquals(this.board.getArray().length, 0);
    assertEquals(this.board.getPlaceableMap().size(), 0);

  }

  /**
   * Tests that the firstUpdate boolean updates correctly.
   */
  @Test
  public void firstUpdateTest() {
    this.setup(4, 4);
    assertEquals(this.board.getFirstUpdate(), true);
    this.board.updateBoard();
    assertEquals(this.board.getFirstUpdate(), false);
  }

}
