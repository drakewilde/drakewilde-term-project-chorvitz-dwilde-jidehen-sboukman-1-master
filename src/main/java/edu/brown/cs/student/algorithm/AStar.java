package edu.brown.cs.student.algorithm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

import edu.brown.cs.student.game.Edge;
import edu.brown.cs.student.game.Node;

/**
 * This class is used for running A* search the extra distFunct parameter adds a
 * given hueristic to make searching faster.
 *
 * @author solomonboukman
 *
 * @param <N> Any node class that implements node
 * @param <E> Any edge class that implements edge
 * @param <D> Any distance function that implements DistanceFunct used to add
 *            hueristic
 */
public class AStar<N extends Node<E>, E extends Edge<N, E>, D extends DistanceFunct<N, E>> {

  private D distFunct;

  /**
   * Initializes variables.
   * @param distfunct Distance Calculator.
   */
  public AStar(D distfunct) {

    distFunct = distfunct;

  }

  /**
   * Sets the distance calculator.
   * @param inDistfunct Distance calculator.
   */
  public void setDistFunct(D inDistfunct) {
    distFunct = inDistfunct;
  }

  /**
   * This is the central search algorithm for Dijkstra's. It takes in the Node of
   * the source, as well as the ID of the destination node. A design choice was
   * made not to take in to "V" nodes, since the destination may not exist.
   * Instead, we ask for its id.
   *
   * @param source    The source node
   * @param id        The id of the destination
   * @return A list of edges which span the shortest path
   */
  public ArrayList<E> search(N source, Number id) {
    HashMap<Number, Double> distMap = new HashMap<Number, Double>();
    HashMap<N, E> prevMap = new HashMap<N, E>();
    PriorityQueue<N> pq = new PriorityQueue<N>(new DistComp(distMap));
    HashSet<N> visited = new HashSet<N>();
    N finalDest = null;
    ArrayList<E> shortestPath = new ArrayList<E>();
    distMap.put(source.getId(), 0.0);
    prevMap.put(source, null);
    pq.clear();
    pq.add(source);
    while (pq.size() > 0) {
      // If we pop from the PQ, we have visited the node
      N curr = pq.remove();
      if (visited.contains(curr)) {
        continue;
      } else {
        visited.add(curr);
      }
      if (curr.getId() == id) {
        finalDest = curr;
        break;
      }
      List<E> outgoing = curr.getEdges();
      for (E o : outgoing) {
        N dest = o.getDestination();
        if (!distMap.containsKey(dest.getId())) {
          distMap.put(dest.getId(), Double.POSITIVE_INFINITY);
        }
        double currDist = distMap.get(dest.getId());
        double newDist = distMap.get(curr.getId()) + o.getWeight() + distFunct.dist(dest);
        if (newDist < currDist) {
          if (pq.contains(dest)) {
            pq.remove(dest);
          }
          // dest.setDist(curr.getDist() + o.getWeight());
          distMap.replace(dest.getId(), newDist);
          if (!prevMap.containsKey(dest)) {
            prevMap.put(dest, o);
          } else {
            prevMap.replace(dest, o);
          }
          // System.out.println(dest.getId() + " just had its prev set to " +
          // curr.getId());
          // System.out.println("Added to PQ: " + dest.getId());
          pq.add(dest);
        }
      }
    }

    E temp = prevMap.get(finalDest);
    while (temp != null) {
      shortestPath.add(0, temp);
      temp = prevMap.get(temp.getSource());
    }
    return shortestPath;
  }

  /**
   * This class models the comparator to be used in the heap, where the distances
   * of the nodes stored in the hashmap are used to sort.
   */
  private class DistComp implements Comparator<N> {
    private HashMap<Number, Double> distMap;

    /**
     * The constructor for the comparator simply takes in the map.
     *
     * @param inputMap The map of values
     */
    DistComp(HashMap<Number, Double> inputMap) {
      distMap = inputMap;
    }

    /**
     * The compare method returns which of the two vertices is of greater value.
     *
     * @return Whether or not the first vertex was greater than the second
     */
    @Override
    public int compare(N one, N two) {
      if (Double.compare(distMap.get(one.getId()), distMap.get(two.getId())) > 0) {
        return 1;
      }
      return -1;
    }

  }

}
