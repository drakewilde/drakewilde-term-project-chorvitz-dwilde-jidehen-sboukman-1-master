package edu.brown.cs.student.game;

/**
 * Edge between two GameNodes.
 *
 */
public class GameEdge implements Edge<GameNode, GameEdge> {

  private GameNode source, destination;
  private Double weight;
  private Boolean traversable;

  /**
   * Creates edge between two nodes.
   * @param s Source Node.
   * @param d Destination Node.
   * @param w Edge weight.
   */
  public GameEdge(GameNode s, GameNode d, Double w) {
    this.source = s;
    this.destination = d;
    this.weight = w;
    this.traversable = true;
  }

  @Override
  public GameNode getSource() {
    return this.source;
  }

  @Override
  public GameNode getDestination() {
    return this.destination;
  }

  @Override
  public Double getWeight() {
    return this.weight;
  }

  @Override
  public void setTraversable(Boolean t) {
    this.traversable = t;
  }

  @Override
  public Boolean isTraversable() {
    return this.traversable;
  }

}
