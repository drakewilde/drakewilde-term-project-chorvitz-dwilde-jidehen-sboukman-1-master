package edu.brown.cs.student.game;
import org.junit.Test;

public class GameNodeTest {


  @Test
  public void getEdgesTest(){
    GameNode g = new GameNode(0,0,0);
    assert(g.getEdges().size() == 0);
    GameNode g1 = new GameNode(0,0,0);
    GameEdge e = new GameEdge(g, g1, 1.);
    g.addEdge(e);
    assert(g.getEdges().size() == 1);
  }

  @Test
  public void getId() {
    GameNode g = new GameNode(0,0,0);
    assert ((int) g.getId() == 0);
  }
  @Test
  public void getCoord() {
    GameNode g = new GameNode(0,0,0);
    assert(g.getCoordinate().getX().equals(0));
    assert(g.getCoordinate().getY().equals(0));
  }
}
