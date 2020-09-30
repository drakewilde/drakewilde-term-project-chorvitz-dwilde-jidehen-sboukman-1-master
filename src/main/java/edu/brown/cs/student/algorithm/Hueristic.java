package edu.brown.cs.student.algorithm;

import edu.brown.cs.student.game.GameEdge;
import edu.brown.cs.student.game.GameNode;

/**
 * Heuristic distance calculator.
 *
 */
public class Hueristic implements DistanceFunct<GameNode, GameEdge> {

  private GameNode destNode;
  private int xCoord;
  private int yCoord;

  /**
   * Initializes destination node.
   * @param dest Destination node.
   */
  public Hueristic(GameNode dest) {
    destNode = dest;
  }

  @Override
  public Double dist(GameNode gamenode) {
    int inX = gamenode.getCoordinate().getX();
    int inY = gamenode.getCoordinate().getY();
    int term1 = xCoord - inX;
    int term2 = yCoord - inY;
    return Math.sqrt((term1 * term1) + (term2 * term2));
  }

}
