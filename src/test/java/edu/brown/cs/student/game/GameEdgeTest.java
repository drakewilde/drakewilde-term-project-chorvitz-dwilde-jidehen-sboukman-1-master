package edu.brown.cs.student.game;

import org.junit.Test;

public class GameEdgeTest {

  @Test
  public void destinationAndSourceTest() {
    GameNode n1 = new GameNode(0,0,0);
    GameNode n2 = new GameNode(0,1,1);
    GameEdge e = new GameEdge(n1, n2, 1.);
    assert(e.getSource().equals(n1));
    assert (e.getDestination().equals(n2));
  }

  @Test
  public void weightTest() {
    GameNode n1 = new GameNode(0,0,0);
    GameNode n2 = new GameNode(0,1,1);
    GameEdge e = new GameEdge(n1, n2, 1.);
    assert(e.getWeight() == 1.);
  }

  @Test
  public void traversableTest() {
    GameNode n1 = new GameNode(0,0,0);
    GameNode n2 = new GameNode(0,1,1);
    GameEdge e = new GameEdge(n1, n2, 1.);
    assert (e.isTraversable());
    e.setTraversable(false);
    assert(!e.isTraversable());
  }

}
