package edu.brown.cs.student.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.brown.cs.student.algorithm.AStar;
import edu.brown.cs.student.algorithm.Coordinate;
import edu.brown.cs.student.algorithm.Hueristic;
import edu.brown.cs.student.algorithm.KDcomparator;
import edu.brown.cs.student.algorithm.KDtree;
import edu.brown.cs.student.objects.Placeable;

/**
 * Board contains all back end logic for the game.
 *
 */
public class Board {

  private Integer dimension;
  private static final double EDGE_WEIGHT = 1.0;
  private GameNode[][] nodeArray;
  private List<Placeable> placeables;
  private AStar<GameNode, GameEdge, Hueristic> pathfinder;
  private Map<Integer, Placeable> placeableMap;
  private List<Placeable> infected, uninfected, nonTargeted;
  private Map<Number, List<Coordinate>> allPaths;
  private Map<Placeable, Placeable> personToTargetMap;
  private Boolean firstUpdate;
  private Placeable startNode;

  /**
   * Initializes variables and sets up board and lists to track board contents.
   * @param d Dimensions of board.
   * @param s Starting placeable.
   * @param ps List of all placeables.
   */
  public Board(Integer d, Placeable s, List<Placeable> ps) {
    this.dimension = d;
    this.startNode = s;
    this.nodeArray = new GameNode[this.dimension][this.dimension];
    this.placeables = ps;
    infected = new ArrayList<>();
    uninfected = new ArrayList<>();
    nonTargeted = new ArrayList<>();
    placeableMap = new HashMap<>();
    allPaths = new HashMap<>();
    personToTargetMap = new HashMap<>();
    firstUpdate = true;
    this.loadObjects();
    this.makeBoard();
    this.infected.add(s);
  }

  /**
   * Loads objects into placeables list.
   */
  public void loadObjects () {
    for (Placeable p : this.placeables) {
      placeableMap.put(p.getId(), p);
      if (p.getType() == "Person") {
        nonTargeted.add(p);
        uninfected.add(p);
      }
    }
  }

  /**
   * Makes the board.
   */
  private void makeBoard() {
    int currId = 0;
    for (int col = 0; col < this.dimension; col++) {
      for (int row = 0; row < this.dimension; row++) {
        GameNode node = new GameNode(col, row, currId);
        this.nodeArray[col][row] = node;
        currId += 1;
      }
    }
    for (Placeable p : this.placeables) {
      placeableMap.put(p.getId(), p);
      if (p.getType() != "Person") {
        this.nodeArray[p.getCoordinates().getX()][p.getCoordinates().getY()] = null;
      }
    }
    this.addEdges();
  }

  /**
   * Adds edges to the board.
   */
  private void addEdges() {
    for (int col = 0; col < this.dimension; col++) {
      for (int row = 0; row < this.dimension; row++) {
        if (this.nodeArray[col][row] != null) {
          GameNode currNode = this.nodeArray[col][row];
          if (col != this.dimension - 1 && this.nodeArray[col + 1][row] != null) {
            GameEdge rightEdge = new GameEdge(currNode, this.nodeArray[col + 1][row], EDGE_WEIGHT);
            currNode.addEdge(rightEdge);
          }
          if (col != 0 && this.nodeArray[col - 1][row] != null) {
            GameEdge leftEdge = new GameEdge(currNode, this.nodeArray[col - 1][row], EDGE_WEIGHT);
            currNode.addEdge(leftEdge);
          }
          if (row != 0 && this.nodeArray[col][row - 1] != null) {
            GameEdge aboveEdge = new GameEdge(currNode, this.nodeArray[col][row - 1], EDGE_WEIGHT);
            currNode.addEdge(aboveEdge);
          }
          if (row != this.dimension - 1 && this.nodeArray[col][row + 1] != null) {
            GameEdge belowEdge = new GameEdge(currNode, this.nodeArray[col][row + 1], EDGE_WEIGHT);
            currNode.addEdge(belowEdge);
          }
        }
      }
    }
  }

  /**
   * Updates the contents of the board.
   */
  public void updateBoard() {
    if (firstUpdate) {
      this.firstUpdate();
    } else {
      this.restUpdates();
    }
  }

  /**
   * Called on the first update of the board. Since there are no infected people yet.
   */
  public void firstUpdate() {
    Placeable firstInfected = null;
    for(Placeable p : uninfected) {
      allPaths.put(p.getId(), new ArrayList<Coordinate>());
      if (p.getId().equals(this.startNode.getId())) {
        firstInfected = p;
      }
    }

    this.infected.add(firstInfected);
    this.uninfected.remove(firstInfected);

    if (this.uninfected.size() > 0) {
      KDtree<Placeable> tree = new KDtree<>(new KDcomparator<Placeable>(), uninfected, -1, 2);
      for (Placeable p : this.infected) {
        tree.setPos(p.getCoordinates());
        tree.search(tree);
        Placeable firstTarget = tree.getClosest().get(0);
        personToTargetMap.put(p, firstTarget);
        Coordinate targetCoord = firstTarget.getCoordinates();
        Coordinate infectedCoord = p.getCoordinates();
        GameNode source = nodeArray[infectedCoord.getX()][infectedCoord.getY()];
        GameNode target = nodeArray[targetCoord.getX()][targetCoord.getY()];

        pathfinder = new AStar<GameNode, GameEdge, Hueristic>(new Hueristic(target));
        List<GameEdge> path = pathfinder.search(source, target.getId());
        List<Coordinate> pathList = this.makeCoordPath(path);
        allPaths.replace(p.getId(), pathList);

      }

      this.firstUpdate = false;

    }
  }

  /**
   * Called to update board on all subsequent calls after firstUpdate.
   */
  public void restUpdates() {

    for (Placeable p : infected) {
      if (!personToTargetMap.containsKey(p)) {
        Coordinate srcCoord = p.getCoordinates();
        if (nonTargeted.size() > 0) {
          Placeable targetedPerson;
          if (nonTargeted.size() == 1) {
            targetedPerson = nonTargeted.get(0);
          } else {
            KDtree<Placeable> tree = new KDtree<Placeable>(new KDcomparator<Placeable>(), nonTargeted, -1, 2);
            tree.setPos(srcCoord);
            tree.search(tree);
            targetedPerson = tree.getClosest().get(0);

          }
          Coordinate targetCoord = targetedPerson.getCoordinates();
          personToTargetMap.put(p, targetedPerson);
          nonTargeted.remove(targetedPerson);
          GameNode source = nodeArray[srcCoord.getX()][srcCoord.getY()];
          GameNode target = nodeArray[targetCoord.getX()][targetCoord.getY()];
          pathfinder.setDistFunct(new Hueristic(target));
          List<GameEdge> path = pathfinder.search(source, target.getId());
          List<Coordinate> coordPath = this.makeCoordPath(path);
          allPaths.replace(p.getId(), coordPath);
        }

      }
    }


    for (Placeable p : placeables) {
      if (infected.contains(p)) {
        if (allPaths.get(p.getId()).size() > 1) {
          p.updateCoordinate(allPaths.get(p.getId()).get(0).getX(), allPaths.get(p.getId()).get(0).getY());
          allPaths.get(p.getId()).remove(0);
          if (allPaths.get(p.getId()).size() == 1) {
            infected.add(personToTargetMap.get(p));
            uninfected.remove(personToTargetMap.get(p));
            personToTargetMap.remove(p);
          }
        }
      }
    }
  }

  /**
   * Gets the coordinate path between nodes.
   * @param path List of edges.
   * @return List of coordinates in the path.
   */
  public List<Coordinate> makeCoordPath(List<GameEdge> path) {
    List<Coordinate> pathList = new ArrayList<Coordinate>();
    for (int i = 0; i < path.size(); i++) {
      if (i == 0) {
        GameNode s = path.get(i).getSource();
        GameNode d = path.get(i).getDestination();
        pathList.add(s.getCoordinate());
        pathList.add(d.getCoordinate());
      } else {
        GameNode d = path.get(i).getDestination();
        pathList.add(d.getCoordinate());
      }
    }
    return pathList;
  }

  /**
   * Adds an infected person to the game.
   * @param id Id of infected person.
   */
  public void addInfected(Integer id) {
    Placeable p = this.placeableMap.get(id);
    this.uninfected.remove(p);
    this.infected.add(p);
  }


  /**
   * Clears the person's path.
   * @param id Id of person to clear path of.
   */
  public void clearPath(Integer id) {
    this.allPaths.get(id).clear();
  }

  /**
   * Gets all paths on the board.
   * @return List of all paths on the board.
   */

  public Map<Number, List<Coordinate>> getAllPaths() {
    return this.allPaths;
  }

  /**
   * Gets list of all infected people on the board.
   * @return List of all infected people.
   */
  public List<Placeable> getInfectedList() {
    return this.infected;
  }

  /**
   * Gets list of all healthy people on the board.
   * @return List of all healthy people.
   */
  public List<Placeable> getUninfectedList() {
    return this.uninfected;
  }

  /**
   * Gets the dimension of the square. Ex 2 is a 2 by 2 grid.
   * @return Integer representing dimension.
   */
  public Integer getDimension() {
    return this.dimension;
  }

  /**
   * Gets map of placeables to ids.
   * @return Map of placeables to ids.
   */
  public Map<Integer, Placeable> getPlaceableMap() {
    return this.placeableMap;
  }

  /**
   * Returns 2d array of nodes for testing.
   * @return 2d array of nodes.
   */
  public GameNode[][] getArray() {
    return this.nodeArray;
  }

  /**
   * Returns the first update boolean for testing.
   * @return first update boolean.
   */
  public Boolean getFirstUpdate() {
    return this.firstUpdate;
  }

}
