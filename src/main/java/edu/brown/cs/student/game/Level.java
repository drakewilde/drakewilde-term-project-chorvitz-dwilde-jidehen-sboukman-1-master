package edu.brown.cs.student.game;

import java.util.List;
import java.util.Map;

import edu.brown.cs.student.algorithm.Coordinate;
import edu.brown.cs.student.objects.Placeable;

/**
 * A level in the game.
 *
 */
public class Level {

  private Integer dimension, numMoves, id;
  private List<Placeable> placeables;
  private transient Board board, boardForReset;
  private Map<Number, List<Coordinate>> allPathsFromBoard;
  private List<Placeable> infected;
  private List<Placeable> uninfected;
  private Placeable startNode;
  private Double percent;

  /**
   * Creates instance of level that holds all attributes of a level in the game.
   * @param d Dimensions of board.
   * @param pct Score needed to pass level.
   * @param s Starting node.
   * @param n Number of moves allowed.
   * @param p List of all Placeables.
   * @param id Level id.
   */
  public Level(Integer d, Double pct, Placeable s, Integer n, List<Placeable> p, Integer id) {


    this.id = id;
    this.dimension = d;
    this.startNode = s;
    this.placeables = p;
    this.numMoves = n;

    this.percent = Double.valueOf(pct / 100.);

    this.board = new Board(this.dimension, this.startNode, this.placeables);
    allPathsFromBoard = this.board.getAllPaths();
    infected = this.board.getInfectedList();
    uninfected = this.board.getUninfectedList();
  }

  /**
   * Gets all placeables in level.
   * @return List of placeables.
   */
  public List<Placeable> getPlaceables() {
    return this.placeables;
  }

  /**
   * Gets board dimensions of level.
   * @return Board dimensions.
   */
  public Integer getDimension() {
    return this.dimension;
  }

  /**
   * Gets the board.
   * @return Game board.
   */
  public Board getBoard() {
    return this.board;
  }

}

